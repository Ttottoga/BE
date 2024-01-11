package com.umc.ttg.domain.member.entity;

import com.umc.ttg.global.util.Time;
import jakarta.persistence.*;

@Entity
public class Member extends Time {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false, length = 20)
    private String nickname;

    @Column(length = 15)
    private String phone_num;

    @Column(nullable = false, length = 30)
    private String email;

    @Column(nullable = false)
    private int benefit_count;
}
