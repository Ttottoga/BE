package com.umc.ttg.domain.store.application;

import com.umc.ttg.domain.store.dto.CreateStoreResponse;
import com.umc.ttg.domain.store.entity.Store;
import com.umc.ttg.global.common.BaseResponseDto;

public interface StoreCommandService {

    BaseResponseDto<CreateStoreResponse> save(Store store, Long menuId, Long regionId);

}
