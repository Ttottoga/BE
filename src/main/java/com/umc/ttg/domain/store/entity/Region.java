package com.umc.ttg.domain.store.entity;

import jakarta.persistence.*;

@Entity
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false, length = 10)
    private String name;

    private Long upperId;

}
