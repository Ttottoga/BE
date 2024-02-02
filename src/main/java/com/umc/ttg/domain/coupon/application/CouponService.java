package com.umc.ttg.domain.coupon.application;

import com.umc.ttg.domain.coupon.dto.CouponResponseDto;
import com.umc.ttg.global.common.BaseResponseDto;

import java.util.List;

public interface CouponService {
    BaseResponseDto<List<CouponResponseDto>> getAllCoupons(Long memberId);
    BaseResponseDto<CouponResponseDto> getCouponDetails(Long memberId, Long couponId);
}
