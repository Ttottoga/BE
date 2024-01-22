package com.umc.ttg.domain.store.entity;

import com.umc.ttg.global.util.Time;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
public class Store extends Time {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false, length = 100)
    private String subTitle;

    private String image;

    private String useInfo;

    private String saleInfo;

    private String placeInfo;

    private String sponInfo;

    private String serviceInfo;

    @Column(nullable = false, length = 1)
    @ColumnDefault("'n'")
    private char hotYn;

    @Column(nullable = false)
    private int reviewCount;

    @Column(nullable = false)
    private int reviewSpan;

    @Column(nullable = false)
    private String address;

    @OneToOne
    @JoinColumn(name = "region_id")
    private Region region;

    @OneToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;

}
