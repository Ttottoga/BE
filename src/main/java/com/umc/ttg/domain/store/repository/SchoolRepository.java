package com.umc.ttg.domain.store.repository;

import com.umc.ttg.domain.store.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SchoolRepository extends JpaRepository<School, Long> {
    Optional<School> findById(Long id);
}
