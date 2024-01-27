package com.umc.ttg.domain.store.dto.converter;

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
}