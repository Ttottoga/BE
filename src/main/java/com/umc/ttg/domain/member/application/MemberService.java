package com.umc.ttg.domain.member.application;

import com.umc.ttg.domain.member.entity.Member;

import java.util.List;

public interface MemberService {
    public List<Member> findAll();
    public Member findMemberByUsername(String username);
}
