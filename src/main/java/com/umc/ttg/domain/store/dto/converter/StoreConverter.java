package com.umc.ttg.domain.store.dto.converter;

import com.umc.ttg.domain.store.dto.CreateStoreRequest;
import com.umc.ttg.domain.store.dto.CreateStoreResponse;
import com.umc.ttg.domain.store.entity.Menu;
import com.umc.ttg.domain.store.entity.Region;
import com.umc.ttg.domain.store.entity.Store;
import org.springframework.web.multipart.MultipartFile;

public class StoreConverter {

    // MultiPartFile -> S3 링크로
    public static String toS3ImageLink(MultipartFile multipartFile) {
        return "이미지 링크";
    }

    // Store 정보 -> CreateStoreResponse 로
    public static CreateStoreResponse toCreateStoreResponse(Long storeId) {
        return new CreateStoreResponse(storeId);
    }
}