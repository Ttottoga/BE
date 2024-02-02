package com.umc.ttg.domain.store.dto;

import com.umc.ttg.domain.coupon.dto.CouponResponseDto;
import com.umc.ttg.domain.coupon.dto.MyPageCouponResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyPageStoreResponseDto {

    private Long storeId;
    private String title;
    private String image;
    private MyPageCouponResponseDTO couponDto;
}
