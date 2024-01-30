package com.umc.ttg.domain.store.dto.converter;

import com.umc.ttg.domain.review.entity.Review;
import com.umc.ttg.domain.store.dto.StoreCreateResponseDto;
import com.umc.ttg.domain.store.entity.Store;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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

    // Review Link -> review Image
    public static String convertToReviewImage(Review review) {
        return "리뷰 테스트";
    }

    // Review Link -> review profileImage
    public static String convertToProfileImage(Review review) {
        return "리뷰 프로필 테스트";
    }

    // Review Link -> review nickname
    public static String convertToNickname(Review review) {
        return "리뷰 닉네임";
    }

    // Review Link -> reviewTitle
    public static String convertToReviewTitle(Review review) {
        return "리뷰 타이틀";
    }

}