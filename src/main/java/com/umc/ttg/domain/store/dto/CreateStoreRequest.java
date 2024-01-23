package com.umc.ttg.domain.store.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateStoreRequest {

    private String title;
    private String subTitle;
    private Long region;
    private Long menu;
    private MultipartFile storeImage;
    private String serviceInfo;
    private int reviewSpan;
    private String useInfo;
    private String saleInfo;
    private String sponInfo;
    private String placeInfo;
    private String address;

}
