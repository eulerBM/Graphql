package com.example.graphqlserver.Repository;

import com.example.graphqlserver.Entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    List<ProductEntity> findByNameContainingIgnoreCase(String name);
}
