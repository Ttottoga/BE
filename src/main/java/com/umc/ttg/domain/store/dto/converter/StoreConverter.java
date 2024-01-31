package com.umc.ttg.domain.store.dto.converter;

import com.umc.ttg.domain.review.entity.Review;
import com.umc.ttg.domain.store.dto.StoreCreateResponseDto;
import org.springframework.web.multipart.MultipartFile;

public class StoreConverter {

    // MultiPartFile -> S3 링크로
    public static String convertToS3ImageLink(MultipartFile multipartFile) {
        return "이미지 링크";
    }

    // Store 정보 -> CreateStoreResponse 로
    public static StoreCreateResponseDto convertToCreateStoreResponse(Long storeId) {

        return new StoreCreateResponseDto(storeId);

    }

    // 리뷰 썸네일 -> 상점 이미지
    public static String convertToReviewImage(Review review) {

        return review.getStore().getImage();

    }

    // 리뷰 작성자 이미지 -> Member 프로필 이미지
    public static String convertToReviewProfileImage(Review review) {

        return review.getMember().getProfileImage();

    }

    // 리뷰 작성자 -> Member 닉네임
    public static String convertToReviewNickname(Review review) {

        return review.getMember().getNickname();

    }

    // 리뷰 타이틀 -> {Member 닉네임}'님의 닉네임'
    public static String convertToReviewTitle(Review review) {

        return review.getMember().getNickname() + " 님의 닉네임";

    }

}