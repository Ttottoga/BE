package com.umc.ttg.domain.store.repository;

import com.umc.ttg.domain.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
}
