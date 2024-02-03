package com.umc.ttg.domain.store.dto.converter;

import com.umc.ttg.domain.store.dto.StoreCreateResponseDto;
import com.umc.ttg.domain.store.dto.StoreFindResponseDto;
import com.umc.ttg.domain.store.entity.Store;

public class StoreConverter {

    // Store 정보 -> StoreCreateResponseDto 로
    public static StoreCreateResponseDto convertToCreateStoreResponse(Long storeId) {
        return new StoreCreateResponseDto(storeId);
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