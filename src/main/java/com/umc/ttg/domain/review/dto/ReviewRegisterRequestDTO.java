package com.umc.ttg.domain.review.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewRegisterRequestDTO {

    /*
    @NotNull
    private Long memberId;

    @NotNull
    private Long StoreId;
     */
    @NotBlank
    private String reviewLink;

    // private ReviewStatus status;
}
