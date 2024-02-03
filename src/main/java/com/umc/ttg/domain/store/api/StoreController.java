package com.umc.ttg.domain.store.api;

import com.umc.ttg.domain.member.entity.Member;
import com.umc.ttg.domain.store.application.StoreCommandService;
import com.umc.ttg.domain.store.dto.HomeResponseDto;
import com.umc.ttg.domain.store.dto.StoreCreateRequestDto;
import com.umc.ttg.domain.store.dto.StoreCreateResponseDto;
import com.umc.ttg.domain.store.dto.StoreFindResponseDto;
import com.umc.ttg.domain.store.repository.StoreRepository;
import com.umc.ttg.global.common.BaseResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
@Slf4j
public class StoreController {

    private final StoreCommandService storeCommandService;

    @PostMapping
    public BaseResponseDto<StoreCreateResponseDto> createStore(@ModelAttribute @Valid StoreCreateRequestDto storeCreateRequestDto) throws IOException {

        return storeCommandService.saveStore(storeCreateRequestDto);

    }

    @GetMapping("/{store-id}")
    public BaseResponseDto<StoreFindResponseDto> findStore(@PathVariable("store-id") Long storeId) {

        return storeCommandService.findStore(storeId);

    }

    @GetMapping("/home")
    public BaseResponseDto<HomeResponseDto> home() {

        /**
         * 여기에 토큰으로부터 MemberId 로직 들어갈 것
         */

        // Test MemberId
        Long testMemberId = 1L;

        BaseResponseDto<HomeResponseDto> home = storeCommandService.getHome(testMemberId);

        return home;

    }

}
