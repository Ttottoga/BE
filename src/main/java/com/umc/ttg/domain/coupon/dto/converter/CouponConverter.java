package com.umc.ttg.domain.coupon.dto.converter;

import com.umc.ttg.domain.coupon.dto.MyPageCouponResponseDTO;
import com.umc.ttg.domain.coupon.entity.Coupon;

public class CouponConverter {

    public static MyPageCouponResponseDTO convertToMyCouponDto(Coupon coupon) {
        if (coupon == null) {
            return null;
        }

        MyPageCouponResponseDTO couponResponseDTO = new MyPageCouponResponseDTO();
        couponResponseDTO.setId(coupon.getId());
        couponResponseDTO.setContent(coupon.getContent());

        return couponResponseDTO;
    }
}
