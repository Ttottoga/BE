package com.umc.ttg.domain.store.application;

import com.umc.ttg.domain.member.entity.Member;
import com.umc.ttg.domain.member.repository.HeartStoreRepository;
import com.umc.ttg.domain.member.repository.MemberRepository;
import com.umc.ttg.domain.review.entity.Review;
import com.umc.ttg.domain.review.repository.ReviewRepository;
import com.umc.ttg.domain.store.dto.HomeResponseDto;
import com.umc.ttg.domain.store.dto.StoreCreateRequestDto;
import com.umc.ttg.domain.store.dto.StoreFindByRegionResponseDto;
import com.umc.ttg.domain.store.dto.StoreFindResponseDto;
import com.umc.ttg.domain.store.exception.handler.StoreHandler;
import com.umc.ttg.domain.store.dto.StoreCreateResponseDto;
import com.umc.ttg.domain.store.dto.converter.StoreConverter;
import com.umc.ttg.domain.store.entity.Menu;
import com.umc.ttg.domain.store.entity.Region;
import com.umc.ttg.domain.store.entity.Store;
import com.umc.ttg.domain.store.repository.MenuRepository;
import com.umc.ttg.domain.store.repository.RegionRepository;
import com.umc.ttg.domain.store.repository.StoreRepository;
import com.umc.ttg.global.common.AwsS3;
import com.umc.ttg.global.common.BaseResponseDto;
import com.umc.ttg.global.common.ResponseCode;
import com.umc.ttg.global.util.AwsS3Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Comparator;

@Slf4j
@Service
@RequiredArgsConstructor
public class StoreCommandServiceImpl implements StoreCommandService {

    private final AwsS3Service awsS3Service;

    private final StoreRepository storeRepository;
    private final MenuRepository menuRepository;
    private final RegionRepository regionRepository;
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final HeartStoreRepository heartStoreRepository;

    @Override
    public BaseResponseDto<StoreCreateResponseDto> saveStore(StoreCreateRequestDto storeCreateRequestDto) throws IOException {

        Store store = new Store(storeCreateRequestDto);

        Menu menu = menuRepository.findById(storeCreateRequestDto.getMenu())
                .orElseThrow(() -> new StoreHandler(ResponseCode._BAD_REQUEST));

        Region region = regionRepository.findById(storeCreateRequestDto.getRegion())
                .orElseThrow(() -> new StoreHandler(ResponseCode._BAD_REQUEST));

        store.setMenu(menu);
        store.setRegion(region);
        store.setImage(getS3ImageLink(storeCreateRequestDto.getStoreImage()));

        Store savedStore = storeRepository.save(store);

        return BaseResponseDto.onSuccess(StoreConverter.convertToCreateStoreResponse(savedStore.getId()), ResponseCode.OK);

    }

    private String getS3ImageLink(MultipartFile multipartFile) throws IOException {

        AwsS3 storeImage = awsS3Service.upload(multipartFile, "storeImage");

        return storeImage.getPath();

    }

    @Override
    public BaseResponseDto<StoreFindResponseDto> findStore(Long storeId) {

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreHandler(ResponseCode._BAD_REQUEST));

        return BaseResponseDto.onSuccess(StoreConverter.convertToStoreFindResponseDto(store), ResponseCode.OK);

    }

    /**
     *
     * HOT 상점 먼저 랜덤으로 배치 후, 다음은 베스트(또또가 누적 리뷰 순)순으로 배치
     * 한 번의 요청마다 20개씩 넘겨줌(무한 스크롤 방식)
     *
     */
    @Override
    public BaseResponseDto<Page<StoreFindByRegionResponseDto>> findStoreByRegion(Long regionId, int page, int size, Long memberId) {

        Long testMemberId = saveTestMember().getId();

        Member member = memberRepository.findById(testMemberId).orElseThrow(() -> new StoreHandler(ResponseCode.MEMBER_NOT_FOUND));
        Region region = regionRepository.findById(regionId).orElseThrow(() -> new StoreHandler(ResponseCode._BAD_REQUEST));

        Pageable pageable = PageRequest.of(page, size);

        return BaseResponseDto.onSuccess(findAllByRegionOrderBySort(region, member, pageable), ResponseCode.OK);

    }

    private Page<StoreFindByRegionResponseDto> findAllByRegionOrderBySort(Region region, Member member, Pageable pageable) {

        /**
         * 관심 상점 여부
         * HeartStore 에서 Member, Store 로 조회
         */

        Comparator<Store> compare = Comparator
                .comparing(Store::getHotYn)
                .thenComparing(Store::getReviewCount).reversed();

        List<StoreFindByRegionResponseDto> stores =
                storeRepository.findByRegion(region).stream()
                        .sorted(compare)
                        .map(store -> new StoreFindByRegionResponseDto(store.getId(), store.getTitle(), store.getImage(), store.getServiceInfo(), store.getReviewCount(), heartStoreRepository.findByMemberAndStore(member, store).isPresent()))
                        .toList();

        // 다음 페이지 요청 시, offset 정보 활용하여 데이터 선별하여 전달
        int start = Math.toIntExact(pageable.getOffset());
        int end = Math.min((start + pageable.getPageSize()), stores.size());

        return new PageImpl<>(stores.subList(start, end), pageable, stores.size());

    }

    @Override
    public BaseResponseDto<HomeResponseDto> getHome(Long memberId) {

        /**
         * testMember
         */
        Long testMemberId = saveTestMember().getId();

        Member member = memberRepository.findById(testMemberId)
                .orElseThrow(() -> new StoreHandler(ResponseCode.MEMBER_NOT_FOUND));

        // top 15
        List<HomeResponseDto.Top15> top15 = getTop15(member);

        // hotStore - 랜덤으로
        List<HomeResponseDto.HotStore> hotStore = getHotStore();

        // reviews - 랜덤으로
        List<HomeResponseDto.HomeReviews> homeReview = getHomeReview();


        // ResponseDTO
        HomeResponseDto homeResponseDto = HomeResponseDto.builder()
                .top15(top15)
                .hotStores(hotStore)
                .homeReviews(homeReview).build();


        return BaseResponseDto.onSuccess(homeResponseDto, ResponseCode.OK);

    }

    private List<HomeResponseDto.HomeReviews> getHomeReview() {

        List<Review> reviews = reviewRepository.findAll();

        Collections.shuffle(reviews);

        return reviews.stream()
                .limit(5)
                .map(HomeResponseDto.HomeReviews::new)
                .collect(Collectors.toList());

    }

    private List<HomeResponseDto.HotStore> getHotStore() {

        List<HomeResponseDto.HotStore> hotStores = storeRepository.findAll().stream()
                .filter(store -> store.getHotYn().equals('y'))
                .map(HomeResponseDto.HotStore::new).collect(Collectors.toList());

        Collections.shuffle(hotStores);

        return hotStores.stream()
                .limit(5).collect(Collectors.toList());

    }

    private List<HomeResponseDto.Top15> getTop15(Member member) {

        List<HomeResponseDto.Top15> top15 = new ArrayList<>();

        List<Store> topStores = storeRepository
                .findAll(Sort.by(Sort.Direction.DESC, "reviewCount"))
                .stream().limit(15).toList();

        topStores.forEach(store ->
                top15.add(new HomeResponseDto.Top15(store, heartStoreRepository.findByMemberAndStore(member, store).isPresent())));

        return top15;

    }



    private Member saveTestMember() {

        return memberRepository.save(Member.builder()
                .name("test")
                .nickname("ddd")
                .email("test@gmail.com")
                .profileImage("ddd")
                .phoneNum("010")
                .benefitCount(0)
                .build());

    }

}
