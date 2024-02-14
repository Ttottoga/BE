package com.umc.ttg.domain.coupon.application;

import com.google.zxing.WriterException;
import com.umc.ttg.domain.coupon.dto.CouponResponseDto;
import com.umc.ttg.domain.coupon.dto.converter.CouponConverter;
import com.umc.ttg.domain.coupon.entity.Coupon;
import com.umc.ttg.domain.coupon.exception.handler.CouponHandler;
import com.umc.ttg.domain.coupon.repository.CouponRepository;
import com.umc.ttg.domain.member.entity.Member;
import com.umc.ttg.domain.member.repository.MemberRepository;
import com.umc.ttg.global.common.BaseResponseDto;
import com.umc.ttg.global.common.ResponseCode;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {

    private final MemberRepository memberRepository;
    private final CouponRepository couponRepository;

    @Override
    public BaseResponseDto<List<CouponResponseDto>> getAllCoupons(HttpServletRequest request) {
        List<CouponResponseDto> couponResponseDtos = couponRepository
                .findAllByMemberId(getMemberId(request))
                .stream().map(CouponResponseDto::of)
                .collect(Collectors.toList());
        return BaseResponseDto.onSuccess(couponResponseDtos, ResponseCode.OK);
    }
    @Override
    public BaseResponseDto<CouponResponseDto> getCouponDetails(Long couponId, HttpServletRequest request) throws IOException, WriterException {
        Coupon coupon = getCoupon(couponId, request);

        return BaseResponseDto.onSuccess(CouponResponseDto.of(coupon), ResponseCode.OK);
    }

    @Override
    @Transactional
    public BaseResponseDto<String> useCoupon(Long couponId, HttpServletRequest request) {
        Coupon coupon = getCoupon(couponId, request);

        // 직원 확인
        if (coupon.getStatusYn().equals('Y')) {
            coupon.updateStatus('N');
        }

        return BaseResponseDto.onSuccess(CouponConverter.convertToCouponUsage(coupon), ResponseCode.OK);
    }

    private Coupon getCoupon(Long couponId, HttpServletRequest request) {
        Coupon foundCoupon = couponRepository.findByIdAndMemberId(couponId, getMemberId(request))
                .orElseThrow(() -> new CouponHandler(ResponseCode.COUPON_NOT_FOUND));
        return foundCoupon;
    }

    private Long getMemberId(HttpServletRequest request) {
        String name = request.getUserPrincipal().getName();
        Member member = memberRepository.findByName(name)
                .orElseThrow(() -> new CouponHandler(ResponseCode.MEMBER_NOT_FOUND));
        return member.getId();
    }

}
