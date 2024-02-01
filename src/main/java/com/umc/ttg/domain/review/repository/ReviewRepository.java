package com.umc.ttg.domain.review.repository;

import com.umc.ttg.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
