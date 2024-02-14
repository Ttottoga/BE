package com.umc.ttg.domain.coupon.application;

import com.google.zxing.WriterException;
import com.umc.ttg.domain.coupon.dto.CouponResponseDto;
import com.umc.ttg.global.common.BaseResponseDto;

import java.io.IOException;
import java.util.List;

public interface CouponService {
    BaseResponseDto<List<CouponResponseDto>> getAllCoupons();
    BaseResponseDto<CouponResponseDto> getCouponDetails(Long couponId) throws IOException, WriterException;
    BaseResponseDto<String> useCoupon(Long couponId);
}
