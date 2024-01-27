package com.umc.ttg.domain.store.application;

import com.umc.ttg.domain.store.dto.StoreCreateRequestDto;
import com.umc.ttg.domain.store.dto.StoreCreateResponseDto;
import com.umc.ttg.domain.store.entity.Store;
import com.umc.ttg.global.common.BaseResponseDto;

public interface StoreCommandService {

    BaseResponseDto<StoreCreateResponseDto> save(StoreCreateRequestDto storeCreateRequestDto);

}
