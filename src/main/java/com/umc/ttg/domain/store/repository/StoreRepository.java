package com.umc.ttg.domain.store.repository;

import com.umc.ttg.domain.store.entity.Menu;
import com.umc.ttg.domain.store.entity.School;
import com.umc.ttg.domain.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoreRepository extends JpaRepository<Store, Long> {

    List<Store> findBySchool(School school);
    List<Store> findByTitleContainingOrNameContaining(String keyword, String name);
    List<Store> findByMenu(Menu menu);

}
