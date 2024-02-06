package com.umc.ttg.domain.store.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class StoreSearchResponseDto {

    private Long storeId;
    private String storeTitle;
    private String storeImage;
    private String serviceInfo;
    private Integer reviewCount;
    private Boolean heartStore;

}
