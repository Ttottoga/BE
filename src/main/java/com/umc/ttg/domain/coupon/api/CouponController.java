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

    // 로그인 구현 전 임시로 pathvariable 통해 사용자 확인
    @GetMapping("/{member-id}")
    public BaseResponseDto<List<CouponResponseDto>> allCouponsGet(@PathVariable("member-id") Long id) {
        return couponService.getAllCoupons(id);
    }

    @GetMapping("/{member-id}/{coupon-id}")
    public BaseResponseDto<CouponResponseDto> CouponDetailsGet(@PathVariable("member-id") Long memberId, @PathVariable("coupon-id") Long couponId) throws IOException, WriterException {
        return couponService.getCouponDetails(memberId, couponId);
    }

    @PutMapping("/{member-id}/{coupon-id}/check")
    public BaseResponseDto<String> CouponUse(@PathVariable("member-id") Long memberId, @PathVariable("coupon-id") Long couponId) throws IOException, WriterException {
        return couponService.useCoupon(memberId, couponId);
    }

}
