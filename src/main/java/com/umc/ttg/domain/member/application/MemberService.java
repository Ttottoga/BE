package com.umc.ttg.domain.member.application;

import com.umc.ttg.domain.member.entity.Member;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface MemberService {
    public List<Member> findAll();
    public Member findMemberByUsername(String username);
    public String retrieveMemberId(HttpServletRequest request);
}
