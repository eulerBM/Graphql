package com.example.graphqlserver.Entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "products")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100,nullable = false)
    private String name;

    @Column(length = 500,nullable = false)
    private String description;

    @Column(nullable = false)
    private BigDecimal price;

}
