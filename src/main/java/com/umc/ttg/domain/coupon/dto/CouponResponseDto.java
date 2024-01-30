package com.umc.ttg.domain.coupon.dto;

import com.umc.ttg.domain.coupon.entity.Coupon;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class CouponResponseDto {
    private Long id;
    private String name;
    private String subtitle;
    private Character useYn;
    private String imageUrl;
    private LocalDate startDate;
    private LocalDate endDate;

    @Builder
    private CouponResponseDto(Long id, String name, String subtitle, Character useYn, String imageUrl, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.name = name;
        this.subtitle = subtitle;
        this.useYn = useYn;
        this.imageUrl = imageUrl;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public static CouponResponseDto of(Coupon coupon) {
        return CouponResponseDto.builder()
                .id(coupon.getId())
                .name(coupon.getName())
                .subtitle(coupon.getContent())
                .useYn(coupon.getStatusYn())
                .imageUrl(coupon.getImageUrl())
                .startDate(coupon.getStartDate())
                .endDate(coupon.getEndDate())
                .build();
    }

}
