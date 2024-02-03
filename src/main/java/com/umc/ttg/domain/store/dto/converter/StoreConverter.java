package com.umc.ttg.domain.store.dto.converter;

import com.umc.ttg.domain.review.entity.Review;
import com.umc.ttg.domain.store.dto.StoreCreateResponseDto;
import com.umc.ttg.domain.store.dto.StoreFindResponseDto;
import com.umc.ttg.domain.store.entity.Store;

public class StoreConverter {

    // Store 정보 -> StoreCreateResponseDto 로
    public static StoreCreateResponseDto convertToCreateStoreResponse(Long storeId) {

        return new StoreCreateResponseDto(storeId);

    }

    // 리뷰 썸네일 -> 상점 이미지
    public static String convertToReviewImage(Review review) {

        return review.getStore().getImage();

    }

    // 리뷰 작성자 이미지 -> Member 프로필 이미지
    public static String convertToReviewProfileImage(Review review) {

        return review.getMember().getProfileImage();

    }

    // 리뷰 작성자 -> Member 닉네임
    public static String convertToReviewNickname(Review review) {

        return review.getMember().getNickname();

    }

    // 리뷰 타이틀 -> {Member 닉네임}'님의 닉네임'
    public static String convertToReviewTitle(Review review) {

        return review.getMember().getNickname() + " 님의 닉네임";

    }

    // Store 정보 -> StoreFindResponseDto 로
    public static StoreFindResponseDto convertToStoreFindResponseDto(Store store) {
        return StoreFindResponseDto.builder()
                .storeImage(store.getImage())
                .title(store.getTitle())
                .subTitle(store.getSubTitle())
                .regionName(store.getRegion().getName())
                .menuName(store.getMenu().getName())
                .serviceInfo(store.getServiceInfo())
                .reviewSpan(store.getReviewSpan())
                .heartStore(store.getHotYn())
                .useInfo(store.getUseInfo())
                .saleInfo(store.getSaleInfo())
                .placeInfo(store.getPlaceInfo())
                .address(store.getAddress())
                .sponInfo(store.getSponInfo())
                .reviewCount(store.getReviewCount())
                .name(store.getName()).build();
    }

}