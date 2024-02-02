package com.umc.ttg.domain.review.repository;

import com.umc.ttg.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByMemberId(Long memberId);
}
