package com.umc.ttg.domain.store.entity;

import com.umc.ttg.domain.store.dto.CreateStoreRequest;
import com.umc.ttg.domain.store.dto.converter.StoreConverter;
import com.umc.ttg.global.util.Time;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
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

    @Builder
    public Store(CreateStoreRequest createStoreRequest) {

        this.title = createStoreRequest.getTitle();
        this.subTitle = createStoreRequest.getSubTitle();
        this.image = StoreConverter.toS3ImageLink(createStoreRequest.getStoreImage());
        this.useInfo = createStoreRequest.getUseInfo();
        this.saleInfo = createStoreRequest.getSaleInfo();
        this.placeInfo = createStoreRequest.getPlaceInfo();
        this.sponInfo = createStoreRequest.getSponInfo();
        this.serviceInfo = createStoreRequest.getServiceInfo();
        this.reviewSpan = createStoreRequest.getReviewSpan();
        this.address = createStoreRequest.getAddress();

    }

    public Store setMenuAndRegion(Menu menu, Region region) {

        this.menu = menu;
        this.region = region;

        return this;
    }

}
