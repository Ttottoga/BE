package com.umc.ttg.domain.member.entity;

import com.umc.ttg.domain.store.entity.Store;
import jakarta.persistence.*;

@Entity
public class HeartStore {

    @Id // 식별자 관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;
}
