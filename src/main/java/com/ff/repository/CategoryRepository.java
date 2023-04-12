package com.ff.repository;

import com.ff.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    @Query(
            value = "select * from category c where c.name = ?1", nativeQuery = true
    )
    CategoryEntity findByname(String name);
}
