package com.umc.ttg.domain.store.api;

import com.umc.ttg.domain.store.application.StoreCommandService;
import com.umc.ttg.domain.store.dto.CreateStoreRequest;
import com.umc.ttg.domain.store.dto.CreateStoreResponse;
import com.umc.ttg.domain.store.entity.Store;
import com.umc.ttg.global.common.BaseResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
@Slf4j
public class StoreController {

    private final StoreCommandService storeCommandService;

    @PostMapping
    public BaseResponseDto<CreateStoreResponse> create(@ModelAttribute CreateStoreRequest createStoreRequest) {

        Store store = new Store(createStoreRequest);

        return storeCommandService.save(store, createStoreRequest.getMenu(), createStoreRequest.getRegion());
    }

}
