package com.umc.ttg.domain.store.api;

import com.umc.ttg.domain.store.application.StoreCommandService;
import com.umc.ttg.domain.store.dto.*;
import com.umc.ttg.global.common.BaseResponseDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
@Slf4j
public class StoreController {

    private final StoreCommandService storeCommandService;

    @PostMapping
    public BaseResponseDto<StoreResponseDto> createStore(@ModelAttribute @Valid StoreRequestDto storeRequestDto) throws IOException {

        return storeCommandService.saveStore(storeRequestDto);

    }

    @GetMapping("/{store-id}")
    public BaseResponseDto<StoreFindResponseDto> findStore(@PathVariable("store-id") Long storeId) {

        /**
         * Header 토큰에서 멤버 ID 받아오는 로직 추가 예정
         */
        Long memberId = 2L;

        return storeCommandService.findStore(storeId, memberId);

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
        Long memberId = 2L;

        return storeCommandService.findStoreByRegion(regionId.orElse(1L), page, size, memberId);

    }

    @GetMapping("/menu-categories")
    public BaseResponseDto<Page<StoreFindByMenuResponseDto>> findStoreByMenu(@RequestParam Optional<Long> menuId,
                                                                             @RequestParam int page,
                                                                             @RequestParam int size) {

        /**
         * Header 토큰에서 멤버 ID 받아오는 로직 추가 예정
         */
        Long memberId = 2L;

        return storeCommandService.findStoreByMenu(menuId.orElse(1L), page, size, memberId);

    }

    @GetMapping("/home")
    public BaseResponseDto<HomeResponseDto> home() {

        /**
         * 여기에 토큰으로부터 MemberId 로직 들어갈 것
         */

        // Test MemberId
        Long testMemberId = 2L;

        BaseResponseDto<HomeResponseDto> home = storeCommandService.getHome(testMemberId);

        return home;

    }

    @GetMapping("/search")
    public BaseResponseDto<Page<StoreSearchResponseDto>> searchStore(@RequestParam(value = "keyword", required = false) String keyword,
                                                                     @RequestParam @PositiveOrZero int page,
                                                                     @RequestParam @PositiveOrZero int size) {

        /**
         * Header 토큰에서 멤버 ID 받아오는 로직 추가 예정
         */
        Long memberId = 2L;

        return storeCommandService.searchStore(keyword, page, size, memberId);

    }

    @PutMapping("/{store-id}")
    public BaseResponseDto<StoreResponseDto> storeUpdate(@ModelAttribute @Valid StoreRequestDto storeRequestDto, @PathVariable("store-id") Long storeId) throws IOException {

        /**
         * Header 토큰에서 멤버 ID 받아오는 로직 추가 예정
         */
        Long memberId = 2L;

        return storeCommandService.updateStore(storeRequestDto, storeId);

    }

}
