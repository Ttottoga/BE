package com.umc.ttg.domain.store.application;

import com.umc.ttg.domain.store.dto.StoreCreateRequestDto;
import com.umc.ttg.domain.store.dto.StoreCreateResponseDto;
import com.umc.ttg.domain.store.dto.StoreFindByRegionResponseDto;
import com.umc.ttg.domain.store.dto.StoreFindResponseDto;
import com.umc.ttg.global.common.BaseResponseDto;
import org.springframework.data.domain.Page;

import java.io.IOException;

public interface StoreCommandService {

    BaseResponseDto<StoreCreateResponseDto> saveStore(StoreCreateRequestDto storeCreateRequestDto) throws IOException;

    BaseResponseDto<StoreFindResponseDto> findStore(Long storeId);

    BaseResponseDto<Page<StoreFindByRegionResponseDto>> findStoreByRegion(Long regionId, int page, int size, Long memberId);

}
