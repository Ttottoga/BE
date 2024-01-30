package com.umc.ttg.domain.store.api;

import com.umc.ttg.domain.member.entity.Member;
import com.umc.ttg.domain.store.application.StoreCommandService;
import com.umc.ttg.domain.store.dto.HomeResponseDto;
import com.umc.ttg.domain.store.dto.StoreCreateRequestDto;
import com.umc.ttg.domain.store.dto.StoreCreateResponseDto;
import com.umc.ttg.domain.store.repository.StoreRepository;
import com.umc.ttg.global.common.BaseResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
@Slf4j
public class StoreController {

    private final StoreCommandService storeCommandService;

    @PostMapping
    public BaseResponseDto<StoreCreateResponseDto> create(@ModelAttribute @Valid StoreCreateRequestDto storeCreateRequestDto) {

        return storeCommandService.save(storeCreateRequestDto);

    }

    @GetMapping("/home")
    public BaseResponseDto<HomeResponseDto> home() {

        // Test Member 객체
        Member testMember = new Member();

        BaseResponseDto<HomeResponseDto> home = storeCommandService.getHome(testMember);

        return home;

    }

}
