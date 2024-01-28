package com.umc.ttg.domain.coupon.application;

import com.umc.ttg.domain.coupon.dto.CouponResponseDto;
import com.umc.ttg.domain.coupon.entity.Coupon;
import com.umc.ttg.domain.coupon.exception.handler.CouponHandler;
import com.umc.ttg.domain.coupon.repository.CouponRepository;
import com.umc.ttg.domain.member.entity.Member;
import com.umc.ttg.domain.member.repository.MemberRepository;
import com.umc.ttg.global.common.BaseResponseDto;
import com.umc.ttg.global.common.ResponseCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CouponService {

    private final MemberRepository memberRepository;
    private final CouponRepository couponRepository;

    // 추후 리뷰 상태 변경 구현 완료 후, reviewStatus == "SUCCESS"인 부분 확인하여 쿠폰 객체 추가

    public BaseResponseDto<List<CouponResponseDto>> getAllCoupons(Long memberId) {
        Optional<Member> foundMember = memberRepository.findById(memberId);
        if (foundMember.isEmpty()){
            throw new CouponHandler(ResponseCode.MEMBER_NOT_FOUND);
        }

        List<Coupon> coupons = couponRepository.findAllByMemberId(foundMember.get().getId());
        List<CouponResponseDto> couponResponseDtos = new ArrayList<>();
        for (Coupon coupon : coupons) {
            couponResponseDtos.add(CouponResponseDto.of(coupon));
        }
        return BaseResponseDto.onSuccess(couponResponseDtos, ResponseCode.OK);
    }


}
