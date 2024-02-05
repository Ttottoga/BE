package com.umc.ttg.domain.review.repository;

import com.umc.ttg.domain.member.entity.Member;
import com.umc.ttg.domain.review.entity.Review;
import com.umc.ttg.domain.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    Optional<Review> findByStoreAndMember(Store store, Member member);
}
