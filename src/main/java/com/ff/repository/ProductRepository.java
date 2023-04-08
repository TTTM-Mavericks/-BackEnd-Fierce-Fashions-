package com.ff.repository;

import com.ff.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    ProductEntity findByName(String name);
    @Query(value = "SELECT p FROM ProductEntity p " +
            "JOIN p.categoryList c WHERE c.name = ?1", nativeQuery = true)
    List<ProductEntity> findByCategory(String cateName);
}
