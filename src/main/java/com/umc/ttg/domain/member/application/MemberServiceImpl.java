package com.umc.ttg.domain.member.application;

import com.umc.ttg.domain.member.entity.Member;
import com.umc.ttg.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
    private final MemberRepository memberRepository;
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Member findMemberByUsername(String username) {
        Member user = null;

        user = memberRepository.findByName(username);
        System.out.println(user);

        return user;
    }
}
