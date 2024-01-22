package com.umc.ttg.domain.store.api;

import com.umc.ttg.domain.store.application.StoreCommandService;
import com.umc.ttg.domain.store.dto.CreateStoreRequest;
import com.umc.ttg.domain.store.dto.CreateStoreResponse;
import com.umc.ttg.domain.store.dto.converter.StoreConverter;
import com.umc.ttg.global.common.BaseResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreController {

    private final StoreCommandService storeCommandService;

    @PostMapping
    public BaseResponseDto<CreateStoreResponse> create(@ModelAttribute CreateStoreRequest createStoreRequest) {
        return storeCommandService.save(createStoreRequest);
    }

}
