package com.umc.ttg.domain.coupon.application;

import com.google.zxing.WriterException;
import com.umc.ttg.domain.coupon.dto.CouponResponseDto;
import com.umc.ttg.global.common.BaseResponseDto;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.List;

public interface CouponService {
    BaseResponseDto<List<CouponResponseDto>> getAllCoupons(HttpServletRequest request);
    BaseResponseDto<CouponResponseDto> getCouponDetails(Long couponId, HttpServletRequest request) throws IOException, WriterException;
    BaseResponseDto<String> useCoupon(Long couponId, HttpServletRequest request);
}
