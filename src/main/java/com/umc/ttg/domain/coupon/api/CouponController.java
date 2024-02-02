package com.umc.ttg.domain.coupon.api;

import com.umc.ttg.domain.coupon.application.CouponService;
import com.umc.ttg.domain.coupon.dto.CouponResponseDto;
import com.umc.ttg.global.common.BaseResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}