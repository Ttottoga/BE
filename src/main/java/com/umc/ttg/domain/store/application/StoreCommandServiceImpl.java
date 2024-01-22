package com.umc.ttg.domain.store.application;

import com.umc.ttg.domain.store.dto.CreateStoreRequest;
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
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StoreCommandServiceImpl implements StoreCommandService {

    private final StoreRepository storeRepository;
    private final MenuRepository menuRepository;
    private final RegionRepository regionRepository;

    @Override
    public BaseResponseDto<CreateStoreResponse> save(CreateStoreRequest createStoreRequest) {

        Optional<Menu> menu = menuRepository.findById(createStoreRequest.getMenu());
        Optional<Region> region = regionRepository.findById(createStoreRequest.getRegion());

        Store savedStore = storeRepository.save(new Store(createStoreRequest, menu.get(), region.get()));

        return BaseResponseDto.onSuccess(StoreConverter.toCreateStoreResponse(savedStore.getId()), ResponseCode.OK);

    }
}
