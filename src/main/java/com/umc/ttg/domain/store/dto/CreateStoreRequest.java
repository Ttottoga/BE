package com.umc.ttg.domain.store.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateStoreRequest {

    private String title;
    private String subTitle;
    private Long region;
    private Long menu;
    private MultipartFile storeImage;
    private String serviceInfo;
    private int reviewTerm;
    private String useInfo;
    private String saleInfo;
    private String sponInfo;
    private String placeInfo;
    private String address;

}
