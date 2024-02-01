package com.umc.ttg.domain.member.application;

import com.umc.ttg.domain.coupon.repository.CouponRepository;
import com.umc.ttg.domain.member.dto.MyPageResponseDto;
import com.umc.ttg.domain.member.entity.Member;
import com.umc.ttg.domain.member.repository.MemberRepository;
import com.umc.ttg.domain.review.entity.Review;
import com.umc.ttg.domain.store.repository.StoreRepository;
import com.umc.ttg.global.common.BaseResponseDto;
import com.umc.ttg.global.common.ResponseCode;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberQueryServiceImpl implements MemberQueryService {

    MemberRepository memberRepository;
    CouponRepository couponRepository;
    ReviewRepository reviewRepository;
    StoreRepository storeRepository;

    @Override
    public BaseResponseDto<MyPageResponseDto> lookUpMyPage() {

        /**
         * 추후에 token을 통해 Member 정보를 가져올 예정
         */
        Long memberId = 1L;

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberHandler(ReponseCode.MEMBER_NOT_FOUND));

        List<Review> reviews = reviewRepository.findAllById(memberId)
                .orElseThrow(() -> new MemberHandler(ReponseCode.MMBER_NOT_FOUND));



        return BaseResponseDto.onSuccess(ResponseCode.OK);
    }
}
