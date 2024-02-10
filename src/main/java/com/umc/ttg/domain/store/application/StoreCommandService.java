package com.umc.ttg.domain.store.application;

import com.umc.ttg.domain.store.dto.*;
import com.umc.ttg.global.common.BaseResponseDto;
import org.springframework.data.domain.Page;

import java.io.IOException;

public interface StoreCommandService {

    BaseResponseDto<StoreResponseDto> saveStore(StoreRequestDto storeRequestDto) throws IOException;

    BaseResponseDto<StoreFindResponseDto> findStore(Long storeId, Long memberId);

    BaseResponseDto<HomeResponseDto> getHome(Long memberId);

    BaseResponseDto<Page<StoreFindByRegionResponseDto>> findStoreByRegion(Long regionId, int page, int size, Long memberId);

    BaseResponseDto<Page<StoreFindByMenuResponseDto>> findStoreByMenu(Long menuId, int page, int size, Long memberId);

    BaseResponseDto<Page<StoreSearchResponseDto>> searchStore(String keyword, int page, int size, Long memberId);

    BaseResponseDto<StoreResponseDto> updateStore(StoreRequestDto storeRequestDto, Long storeId) throws IOException;

    BaseResponseDto<HeartStoreResponseDto> insertHeart(Long storeId);

    BaseResponseDto<HeartStoreResponseDto> deleteHeart(Long storeId);

}
