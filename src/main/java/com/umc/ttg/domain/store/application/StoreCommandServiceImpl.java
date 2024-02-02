package com.umc.ttg.domain.store.application;

import com.umc.ttg.domain.member.entity.Member;
import com.umc.ttg.domain.member.repository.HeartStoreRepository;
import com.umc.ttg.domain.member.repository.MemberRepository;
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
import com.umc.ttg.global.common.BaseResponseDto;
import com.umc.ttg.global.common.ResponseCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class StoreCommandServiceImpl implements StoreCommandService {

    private final StoreRepository storeRepository;
    private final MenuRepository menuRepository;
    private final RegionRepository regionRepository;
    private final HeartStoreRepository heartStoreRepository;
    private final MemberRepository memberRepository;

    @Override
    public BaseResponseDto<StoreCreateResponseDto> saveStore(StoreCreateRequestDto storeCreateRequestDto) {

        Store store = new Store(storeCreateRequestDto);

        Menu menu = menuRepository.findById(storeCreateRequestDto.getMenu())
                .orElseThrow(() -> new StoreHandler(ResponseCode._BAD_REQUEST));

        Region region = regionRepository.findById(storeCreateRequestDto.getRegion())
                .orElseThrow(() -> new StoreHandler(ResponseCode._BAD_REQUEST));

        store.setMenu(menu);
        store.setRegion(region);

        Store savedStore = storeRepository.save(store);

        return BaseResponseDto.onSuccess(StoreConverter.convertToCreateStoreResponse(savedStore.getId()), ResponseCode.OK);

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

        Member member = memberRepository.findById(memberId).orElseThrow(() -> new StoreHandler(ResponseCode.MEMBER_NOT_FOUND));
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

        return new PageImpl<>(stores, pageable, stores.size());

    }

}
