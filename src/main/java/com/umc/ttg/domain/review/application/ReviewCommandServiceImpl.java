package com.umc.ttg.domain.review.application;

import com.umc.ttg.domain.member.entity.Member;
import com.umc.ttg.domain.member.exception.handler.MemberHandler;
import com.umc.ttg.domain.member.repository.MemberRepository;
import com.umc.ttg.domain.review.dto.ReviewRegisterRequestDTO;
import com.umc.ttg.domain.review.dto.ReviewRegisterResponseDTO;
import com.umc.ttg.domain.review.entity.Review;
import com.umc.ttg.domain.review.entity.ReviewStatus;
import com.umc.ttg.domain.review.repository.ReviewRepository;
import com.umc.ttg.domain.store.entity.Store;
import com.umc.ttg.domain.store.exception.handler.StoreHandler;
import com.umc.ttg.domain.store.repository.StoreRepository;
import com.umc.ttg.global.common.BaseResponseDto;
import com.umc.ttg.global.common.ResponseCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReviewCommandServiceImpl implements ReviewCommandService {

    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;

    @Override
    public BaseResponseDto<ReviewRegisterResponseDTO> save(Long storeId, ReviewRegisterRequestDTO reviewRegisterRequestDTO) {

        // 로그인 구현되면 시큐리티에서 member 가져올 예정
        Long memberId = 1L;

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreHandler(ResponseCode.STORE_NOT_FOUND));

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberHandler(ResponseCode.MEMBER_NOT_FOUND));

        Review review = new Review(store, member, reviewRegisterRequestDTO);
        review.setStatus(ReviewStatus.SUCCESS);
        review.setApplyDate(LocalDate.now());

        // DB에 저장
        Review savedReview = reviewRepository.save(review);

        ReviewRegisterResponseDTO reviewRegisterResponseDTO = new ReviewRegisterResponseDTO(savedReview.getId());

        return BaseResponseDto.onSuccess(reviewRegisterResponseDTO, ResponseCode.OK);
    }
}
