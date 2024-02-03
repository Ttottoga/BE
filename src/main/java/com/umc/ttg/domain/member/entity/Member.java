package com.umc.ttg.domain.member.entity;

import com.umc.ttg.global.util.Time;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends Time {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false, length = 20)
    private String nickname;

    private String profileImage;

    @Column(length = 15)
    private String phoneNum;

    @Column(nullable = false, length = 30)
    private String email;

    @Column(nullable = false)
    private int benefitCount;

    @Builder
    public Member(String name, String nickname, String profileImage, String phoneNum, String email, int benefitCount) {
        this.name = name;
        this.nickname = nickname;
        this.profileImage = profileImage;
        this.phoneNum = phoneNum;
        this.email = email;
        this.benefitCount = benefitCount;
    }
}
