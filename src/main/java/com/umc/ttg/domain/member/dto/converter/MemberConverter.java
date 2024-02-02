package com.umc.ttg.domain.member.dto.converter;

import com.umc.ttg.domain.member.dto.MyPageMemberResponseDTO;
import com.umc.ttg.domain.member.entity.Member;

public class MemberConverter {

    public static MyPageMemberResponseDTO convertToMyMemberDto(Member member){

        MyPageMemberResponseDTO memberResponseDTO = new MyPageMemberResponseDTO();

        memberResponseDTO.setMemberId(member.getId());
        memberResponseDTO.setNickname(member.getNickname());
        memberResponseDTO.setBenefitCount(member.getBenefitCount());

        return memberResponseDTO;
    }
}
