package com.umc.ttg.domain.store.entity;

import jakarta.persistence.*;

@Entity
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false, length = 10)
    private String name;

    private Long upper_id;
}
