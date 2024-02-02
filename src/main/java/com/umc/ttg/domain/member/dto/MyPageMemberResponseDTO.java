package com.umc.ttg.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyPageMemberResponseDTO {

    private Long memberId;
    private String nickname;
    private int benefitCount;
}
