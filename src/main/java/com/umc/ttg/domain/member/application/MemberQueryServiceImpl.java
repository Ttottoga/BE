package com.umc.ttg.domain.member.application;

import com.umc.ttg.domain.coupon.repository.CouponRepository;
import com.umc.ttg.domain.member.dto.MyPageAllResponseDto;
import com.umc.ttg.domain.member.dto.converter.MemberConverter;
import com.umc.ttg.domain.member.entity.Member;
import com.umc.ttg.domain.member.exception.handler.MemberHandler;
import com.umc.ttg.domain.member.repository.MemberRepository;
import com.umc.ttg.domain.review.dto.MyPageReviewResponseDTO;
import com.umc.ttg.domain.review.repository.ReviewRepository;
import com.umc.ttg.global.common.BaseResponseDto;
import com.umc.ttg.global.common.ResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberQueryServiceImpl implements MemberQueryService {

    private final MemberRepository memberRepository;
    private final CouponRepository couponRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public BaseResponseDto<MyPageAllResponseDto> lookUp() {

        /**
         * 추후에 token을 통해 Member 정보를 가져올 예정
         */
        Long memberId = 1L;

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberHandler(ResponseCode.MEMBER_NOT_FOUND));

        List<MyPageReviewResponseDTO> reviews = reviewRepository.findAllByMemberId(memberId)
                .stream().map(review -> MyPageReviewResponseDTO.of(review, couponRepository))
                .collect(Collectors.toList());

        MyPageAllResponseDto myPageDto = new MyPageAllResponseDto();
        myPageDto.setMember(MemberConverter.convertToMyMemberDto(member));
        myPageDto.setReviewDtos(reviews);

        return BaseResponseDto.onSuccess(myPageDto, ResponseCode.OK);
    }
}
