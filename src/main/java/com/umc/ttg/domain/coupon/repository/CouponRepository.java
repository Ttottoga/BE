package com.umc.ttg.domain.coupon.repository;

import com.umc.ttg.domain.coupon.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
    List<Coupon> findAllByMemberId(Long memberId);

    Optional<Coupon> findByStoreId(Long storeId);

    Optional<Coupon> findByIdAndMemberId(Long couponId, Long memberId);

}
