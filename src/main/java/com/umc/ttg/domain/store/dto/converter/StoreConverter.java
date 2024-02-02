package com.umc.ttg.domain.store.dto.converter;

import com.umc.ttg.domain.coupon.dto.MyPageCouponResponseDTO;
import com.umc.ttg.domain.coupon.dto.converter.CouponConverter;
import com.umc.ttg.domain.coupon.entity.Coupon;
import com.umc.ttg.domain.coupon.repository.CouponRepository;
import com.umc.ttg.domain.store.dto.MyPageStoreResponseDto;
import com.umc.ttg.domain.store.dto.StoreCreateResponseDto;
import com.umc.ttg.domain.store.dto.StoreFindResponseDto;
import com.umc.ttg.domain.store.entity.Store;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public class StoreConverter {

    // MultiPartFile -> S3 링크로
    public static String convertToS3ImageLink(MultipartFile multipartFile) {
        return "이미지 링크";
    }

    // Store 정보 -> StoreCreateResponseDto 로
    public static StoreCreateResponseDto convertToCreateStoreResponse(Long storeId) {
        return new StoreCreateResponseDto(storeId);
    }

    // Store 정보 -> StoreFindResponseDto 로
    public static StoreFindResponseDto convertToStoreFindResponseDto(Store store) {
        return StoreFindResponseDto.builder()
                .storeImage(store.getImage())
                .title(store.getTitle())
                .subTitle(store.getSubTitle())
                .regionName(store.getRegion().getName())
                .menuName(store.getMenu().getName())
                .serviceInfo(store.getServiceInfo())
                .reviewSpan(store.getReviewSpan())
                .heartStore(store.getHotYn())
                .useInfo(store.getUseInfo())
                .saleInfo(store.getSaleInfo())
                .placeInfo(store.getPlaceInfo())
                .address(store.getAddress())
                .sponInfo(store.getSponInfo())
                .reviewCount(store.getReviewCount()).build();
    }

    /**
     * Store와 CouponRepository를 받아 storeDto와 couponDto를 반환하는 기능
     */
    public static MyPageStoreResponseDto convertToMyStoreDto(Store store, CouponRepository couponRepository) {
        if (store == null) {
            return null;
        }

        MyPageStoreResponseDto storeResponseDto = MyPageStoreResponseDto.builder()
                .storeId(store.getId())
                .title(store.getTitle())
                .image(store.getImage())
                .build();

        // Coupon 정보 설정
        Optional<Coupon> optionalCoupon = couponRepository.findByStoreId(store.getId());

        if (optionalCoupon.isPresent()) {
            MyPageCouponResponseDTO couponResponseDTO = CouponConverter.convertToMyCouponDto(optionalCoupon.get());
            storeResponseDto.setCouponDto(couponResponseDTO);
        }

        return storeResponseDto;
    }
}