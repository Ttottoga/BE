package com.umc.ttg.domain.coupon.api;

import com.google.zxing.WriterException;
import com.umc.ttg.domain.coupon.application.CouponService;
import com.umc.ttg.domain.coupon.dto.CouponResponseDto;
import com.umc.ttg.global.common.BaseResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/coupons")
public class CouponController {

    private final CouponService couponService;

    @GetMapping
    public BaseResponseDto<List<CouponResponseDto>> allCouponsGet() {
        return couponService.getAllCoupons();
    }

    @GetMapping("/{coupon-id}")
    public BaseResponseDto<CouponResponseDto> CouponDetailsGet(@PathVariable("coupon-id") Long couponId) throws IOException, WriterException {
        return couponService.getCouponDetails(couponId);
    }

    @PutMapping("/{coupon-id}/check")
    public BaseResponseDto<String> CouponUse(@PathVariable("coupon-id") Long couponId) throws IOException, WriterException {
        return couponService.useCoupon(couponId);
    }

}
