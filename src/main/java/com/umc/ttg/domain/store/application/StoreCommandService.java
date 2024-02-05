package com.umc.ttg.domain.store.application;

import com.umc.ttg.domain.member.entity.Member;
import com.umc.ttg.domain.store.dto.*;
import com.umc.ttg.global.common.BaseResponseDto;
import org.springframework.data.domain.Page;

import java.io.IOException;
import java.util.Optional;

public interface StoreCommandService {

    BaseResponseDto<StoreCreateResponseDto> saveStore(StoreCreateRequestDto storeCreateRequestDto) throws IOException;

    BaseResponseDto<StoreFindResponseDto> findStore(Long storeId, Long memberId);

    BaseResponseDto<HomeResponseDto> getHome(Long memberId);

    BaseResponseDto<Page<StoreFindByRegionResponseDto>> findStoreByRegion(Long regionId, int page, int size, Long memberId);

    BaseResponseDto<Page<StoreFindByMenuResponseDto>> findStoreByMenu(Long menuId, int page, int size, Long memberId);

    BaseResponseDto<Page<StoreSearchResponseDto>> searchStore(String keyword, int page, int size, Long memberId);

}
