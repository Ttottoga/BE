package com.umc.ttg.domain.store.application;

import com.umc.ttg.domain.store.dto.*;
import com.umc.ttg.global.common.BaseResponseDto;
import org.springframework.data.domain.Page;

public interface StoreQueryService {

    BaseResponseDto<StoreFindResponseDto> findStore(Long storeId, Long memberId);

    BaseResponseDto<HomeResponseDto> getHome(Long memberId);

    BaseResponseDto<Page<StoreResultResponseDto>> findStoreByRegion(Long regionId, int page, int size, Long memberId);

    BaseResponseDto<Page<StoreResultResponseDto>> findStoreByMenu(Long menuId, int page, int size, Long memberId);

    BaseResponseDto<Page<StoreResultResponseDto>> searchStore(String keyword, int page, int size, Long memberId);

}
