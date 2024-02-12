package com.umc.ttg.domain.store.api;

import com.umc.ttg.domain.store.application.StoreCommandService;
import com.umc.ttg.domain.store.application.StoreQueryService;
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
    private final StoreQueryService storeQueryService;

    @PostMapping
    public BaseResponseDto<StoreResponseDto> createStore(@ModelAttribute @Valid StoreRequestDto storeRequestDto) throws IOException {

        return storeCommandService.saveStore(storeRequestDto);

    }

    @GetMapping("/{store-id}")
    public BaseResponseDto<StoreFindResponseDto> findStore(@PathVariable("store-id") Long storeId) {

        /**
         * Header 토큰에서 멤버 ID 받아오는 로직 추가 예정
         * Header 에서 Barer token 이 없을 경우 null 을 반환하는 로직 추가
         */
        Long memberId = 2L;

        return storeQueryService.findStore(storeId, memberId);

    }

    /**
     * 정렬 기준 X : 무조건 베스트순이므로(내림차순)
     * @param regionId default 1L(지역>전체)
     * @return
     */
    @GetMapping("/region-categories")
    public BaseResponseDto<Page<StoreResultResponseDto>> findStoreByRegion(@RequestParam Optional<Long> regionId,
                                                                                 @RequestParam @PositiveOrZero int page,
                                                                                 @RequestParam @PositiveOrZero int size) {

        /**
         * Header 토큰에서 멤버 ID 받아오는 로직 추가 예정
         * Header 에서 Barer token 이 없을 경우 null 을 반환하는 로직 추가
         */
        Long memberId = 2L;

        return storeQueryService.findStoreByRegion(regionId.orElse(1L), page, size, memberId);

    }

    @GetMapping("/menu-categories")
    public BaseResponseDto<Page<StoreResultResponseDto>> findStoreByMenu(@RequestParam Optional<Long> menuId,
                                                                             @RequestParam @PositiveOrZero int page,
                                                                             @RequestParam @PositiveOrZero int size) {

        /**
         * Header 토큰에서 멤버 ID 받아오는 로직 추가 예정
         * Header 에서 Barer token 이 없을 경우 null 을 반환하는 로직 추가
         */
        Long memberId = 2L;

        return storeQueryService.findStoreByMenu(menuId.orElse(1L), page, size, memberId);

    }

    @GetMapping("/home")
    public BaseResponseDto<HomeResponseDto> home() {

        /**
         * Header 토큰에서 멤버 ID 받아오는 로직 추가 예정
         * Header 에서 Barer token 이 없을 경우 null 을 반환하는 로직 추가
         */

        // Test MemberId
        Long memberId = 2L;

        return storeQueryService.getHome(memberId);

    }

    @GetMapping("/search")
    public BaseResponseDto<Page<StoreResultResponseDto>> searchStore(@RequestParam(value = "keyword", required = false) String keyword,
                                                                     @RequestParam @PositiveOrZero int page,
                                                                     @RequestParam @PositiveOrZero int size) {

        /**
         * Header 토큰에서 멤버 ID 받아오는 로직 추가 예정
         * Header 에서 Barer token 이 없을 경우 null 을 반환하는 로직 추가
         */
        Long memberId = 2L;

        return storeQueryService.searchStore(keyword, page, size, memberId);

    }

    @PutMapping("/{store-id}")
    public BaseResponseDto<StoreResponseDto> storeUpdate(@ModelAttribute @Valid StoreRequestDto storeRequestDto, @PathVariable("store-id") Long storeId) throws IOException {

        /**
         * Header 토큰에서 멤버 ID 받아오는 로직 추가 예정
         */
        Long memberId = 2L;

        return storeCommandService.updateStore(storeRequestDto, storeId);

    }

    @PostMapping("/{store-id}/heart")
    public BaseResponseDto<HeartStoreResponseDto> insertHeartStore (@PathVariable("store-id") Long storeId) {

        return storeCommandService.insertHeart(storeId);
    }

    @DeleteMapping("/{store-id}/heart")
    public BaseResponseDto<HeartStoreResponseDto> deleteHeartStore (@PathVariable("store-id") Long storeId) {

        return storeCommandService.deleteHeart(storeId);
    }

}
