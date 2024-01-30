package com.umc.ttg.domain.coupon.entity;

import com.umc.ttg.domain.member.entity.Member;
import com.umc.ttg.domain.store.entity.Store;
import com.umc.ttg.global.util.Time;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;

@DynamicInsert @DynamicUpdate
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Coupon extends Time {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    /* 기획 수정 후 추가
    @Column(columnDefinition = "TEXT", nullable = false)
    private String barcode;
     */

    @Column(nullable = false, length = 100)
    private String content;

    private String imageUrl;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Column(nullable = false, length = 1)
    @ColumnDefault("'n'")
    private Character statusYn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @OneToOne
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @Builder
    private Coupon(String name, String content, String imageUrl, LocalDate startDate, LocalDate endDate, Character statusYn, Member member, Store store) {
        this.name = name;
        this.content = content;
        this.imageUrl = imageUrl;
        this.startDate = startDate;
        this.endDate = endDate;
        this.statusYn = statusYn;
        this.member = member;
        this.store = store;
    }

    public static Coupon of(Store store, LocalDate startDate, LocalDate endDate, Member member) {
        return Coupon.builder()
                .name(store.getTitle())
                .content(store.getSubTitle())
                .imageUrl(store.getImage())
                .startDate(startDate)
                .endDate(endDate)
                .statusYn('N')
                .member(member)
                .build();
    }
}
