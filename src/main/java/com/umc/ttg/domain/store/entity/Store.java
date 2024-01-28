package com.umc.ttg.domain.store.entity;

import com.umc.ttg.domain.store.dto.StoreCreateRequestDto;
import com.umc.ttg.domain.store.dto.converter.StoreConverter;
import com.umc.ttg.global.util.Time;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert @DynamicUpdate
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
    private Character hotYn;

    @Column(nullable = false)
    private int reviewCount;

    @Column(nullable = false)
    private int reviewSpan;

    @Column(nullable = false)
    private String address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @Builder
    public Store(StoreCreateRequestDto storeCreateRequestDto) {

        this.title = storeCreateRequestDto.getTitle();
        this.subTitle = storeCreateRequestDto.getSubTitle();
        this.image = StoreConverter.convertToS3ImageLink(storeCreateRequestDto.getStoreImage());
        this.useInfo = storeCreateRequestDto.getUseInfo();
        this.saleInfo = storeCreateRequestDto.getSaleInfo();
        this.placeInfo = storeCreateRequestDto.getPlaceInfo();
        this.sponInfo = storeCreateRequestDto.getSponInfo();
        this.serviceInfo = storeCreateRequestDto.getServiceInfo();
        this.reviewSpan = storeCreateRequestDto.getReviewSpan();
        this.address = storeCreateRequestDto.getAddress();

    }

}
