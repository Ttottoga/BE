package com.umc.ttg.domain.store.application;

import com.umc.ttg.domain.member.entity.Member;
import com.umc.ttg.domain.review.entity.Review;
import com.umc.ttg.domain.review.repository.ReviewRepository;
import com.umc.ttg.domain.store.dto.HomeResponseDto;
import com.umc.ttg.domain.store.dto.StoreCreateRequestDto;
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
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
@RequiredArgsConstructor
public class StoreCommandServiceImpl implements StoreCommandService {

    private final StoreRepository storeRepository;
    private final MenuRepository menuRepository;
    private final RegionRepository regionRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public BaseResponseDto<StoreCreateResponseDto> save(StoreCreateRequestDto storeCreateRequestDto) {

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
    public BaseResponseDto<HomeResponseDto> getHome(Member member) {

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
                        top15.add(store.getHeartStores().contains(member) ?
                                new HomeResponseDto.Top15(store, true) :
                                new HomeResponseDto.Top15(store, false)));

        return top15;

    }

}
