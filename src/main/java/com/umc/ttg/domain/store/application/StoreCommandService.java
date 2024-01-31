package com.umc.ttg.domain.store.application;

import com.umc.ttg.domain.store.dto.StoreCreateRequestDto;
import com.umc.ttg.domain.store.dto.StoreCreateResponseDto;
import com.umc.ttg.domain.store.dto.StoreFindResponseDto;
import com.umc.ttg.global.common.BaseResponseDto;

public interface StoreCommandService {

    BaseResponseDto<StoreCreateResponseDto> saveStore(StoreCreateRequestDto storeCreateRequestDto);

    BaseResponseDto<StoreFindResponseDto> findStore(Long storeId);

}
