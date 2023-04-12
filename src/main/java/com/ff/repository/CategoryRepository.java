package com.ff.repository;

import com.ff.entity.CategoryEntity;
import jakarta.persistence.Converter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    CategoryEntity findCategoryEntityByName(String name);
    CategoryEntity findByName(String name);

    @Query(
            value = "select c.id, c.name, c.image, c.status, c.product_cate from CategoryEntity as c where c.name = ?1"
    ) CategoryEntity findCateByName(String name);
}
