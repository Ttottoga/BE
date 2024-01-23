package com.umc.ttg.domain.store.application;

import com.umc.ttg.domain.store.dto.CreateStoreResponse;
import com.umc.ttg.domain.store.dto.converter.StoreConverter;
import com.umc.ttg.domain.store.entity.Menu;
import com.umc.ttg.domain.store.entity.Region;
import com.umc.ttg.domain.store.entity.Store;
import com.umc.ttg.domain.store.repository.MenuRepository;
import com.umc.ttg.domain.store.repository.RegionRepository;
import com.umc.ttg.domain.store.repository.StoreRepository;
import com.umc.ttg.global.common.BaseResponseDto;
import com.umc.ttg.global.common.ResponseCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class StoreCommandServiceImpl implements StoreCommandService {

    private final StoreRepository storeRepository;
    private final MenuRepository menuRepository;
    private final RegionRepository regionRepository;

    @Override
    public BaseResponseDto<CreateStoreResponse> save(Store store, Long menuId, Long regionId) {

        Optional<Menu> menu = menuRepository.findById(menuId);
        Optional<Region> region = regionRepository.findById(regionId);

        // menu, region 있는지 예외처리 추가해야 함

        store.setMenuAndRegion(menu.get(), region.get());

        Store savedStore = storeRepository.save(store);

        return BaseResponseDto.onSuccess(StoreConverter.toCreateStoreResponse(savedStore.getId()), ResponseCode.OK);

    }
}
