package com.umc.ttg.domain.store.entity;

import com.umc.ttg.global.util.Time;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
public class Store extends Time {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    private String image;

    private String use_info;

    private String sale_info;

    private String spon_info;

    private String service_info;

    @Column(nullable = false, length = 1)
    @ColumnDefault("n")
    private byte[] hot_yn;

    @Column(nullable = false)
    private int review_count;

    @Column(nullable = false)
    private String address;

    @OneToOne
    @JoinColumn(name = "region_id")
    private Region region;

    @OneToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;

}
