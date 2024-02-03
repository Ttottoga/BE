package com.umc.ttg.domain.coupon.application;

import com.google.zxing.WriterException;
import com.umc.ttg.domain.coupon.dto.CouponResponseDto;
import com.umc.ttg.domain.coupon.entity.Coupon;
import com.umc.ttg.domain.coupon.exception.handler.CouponHandler;
import com.umc.ttg.domain.coupon.repository.CouponRepository;
import com.umc.ttg.domain.coupon.utils.QrCodeGenerator;
import com.umc.ttg.domain.member.entity.Member;
import com.umc.ttg.domain.member.repository.MemberRepository;
import com.umc.ttg.global.common.BaseResponseDto;
import com.umc.ttg.global.common.ResponseCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {

    private final MemberRepository memberRepository;
    private final CouponRepository couponRepository;


    // 추후 리뷰 상태 변경 구현 완료 후, reviewStatus == "SUCCESS"인 부분 확인하여 쿠폰 객체 추가

    @Override
    public BaseResponseDto<List<CouponResponseDto>> getAllCoupons(Long memberId) {
        List<CouponResponseDto> couponResponseDtos = couponRepository
                .findAllByMemberId(getMember(memberId).getId())
                .stream().map(CouponResponseDto::of)
                .collect(Collectors.toList());
        return BaseResponseDto.onSuccess(couponResponseDtos, ResponseCode.OK);
    }

    @Override
    public BaseResponseDto<CouponResponseDto> getCouponDetails(Long memberId, Long couponId) throws IOException, WriterException {
        Coupon foundCoupon = couponRepository.findByIdAndMemberId(couponId, getMember(memberId).getId())
                .orElseThrow(() -> new CouponHandler(ResponseCode.COUPON_NOT_FOUND));

        QrCodeGenerator.generateQrCode(foundCoupon);

        return BaseResponseDto.onSuccess(CouponResponseDto.of(foundCoupon), ResponseCode.OK);
    }

    private Member getMember(Long memberId) {
        Member foundMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new CouponHandler(ResponseCode.MEMBER_NOT_FOUND));
        return foundMember;
    }

}
