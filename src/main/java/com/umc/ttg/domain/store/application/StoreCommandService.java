package com.umc.ttg.domain.store.application;

import com.umc.ttg.domain.store.dto.*;
import com.umc.ttg.global.common.BaseResponseDto;
import org.springframework.data.domain.Page;

import java.io.IOException;

public interface StoreCommandService {

    BaseResponseDto<StoreCreateResponseDto> saveStore(StoreCreateRequestDto storeCreateRequestDto) throws IOException;

}
