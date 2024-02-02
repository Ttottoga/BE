package com.umc.ttg.domain.store.api;

import com.umc.ttg.domain.member.entity.Member;
import com.umc.ttg.domain.store.application.StoreCommandService;
import com.umc.ttg.domain.store.dto.StoreCreateRequestDto;
import com.umc.ttg.domain.store.dto.StoreCreateResponseDto;
import com.umc.ttg.domain.store.dto.StoreFindByRegionResponseDto;
import com.umc.ttg.domain.store.dto.StoreFindResponseDto;
import com.umc.ttg.global.common.BaseResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
@Slf4j
public class StoreController {

    private final StoreCommandService storeCommandService;

    @PostMapping
    public BaseResponseDto<StoreCreateResponseDto> createStore(@ModelAttribute @Valid StoreCreateRequestDto storeCreateRequestDto) {

        return storeCommandService.saveStore(storeCreateRequestDto);

    }

    @GetMapping("/{store-id}")
    public BaseResponseDto<StoreFindResponseDto> findStore(@PathVariable("store-id") Long storeId) {

        return storeCommandService.findStore(storeId);

    }

    /**
     * 정렬 기준 X : 무조건 베스트순이므로(내림차순)
     * @param regionId default 1L(지역>전체)
     * @return
     */
    @GetMapping("/region-categories")
    public BaseResponseDto<Page<StoreFindByRegionResponseDto>> findStoreByRegion(@RequestParam Optional<Long> regionId,
                                                                                 @RequestParam int page,
                                                                                 @RequestParam int size) {

        /**
         * Header 토큰에서 멤버 ID 받아오는 로직 추가 예정
         */
        Long memberId = 1L;

        return storeCommandService.findStoreByRegion(regionId.orElse(1L), page, size, memberId);

    }

}
