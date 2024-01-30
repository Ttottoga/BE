package com.umc.ttg.domain.member.repository;

import com.umc.ttg.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    // Member의 정보가 필요해서 임시로 만들었습니다.
}