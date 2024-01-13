package com.umc.ttg.domain.coupon.entity;

import com.umc.ttg.domain.member.entity.Member;
import com.umc.ttg.domain.store.entity.Store;
import com.umc.ttg.global.util.Time;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import java.time.LocalDate;

@Entity
public class Coupon extends Time {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String barcode;

    @Column(nullable = false, length = 100)
    private String content;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Column(nullable = false, length = 1)
    @ColumnDefault("n")
    private char statusYn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @OneToOne
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

}
