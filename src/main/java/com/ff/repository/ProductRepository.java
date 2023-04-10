package com.ff.repository;

import com.ff.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    ProductEntity findByName(String name);

    @Query(value = "SELECT p FROM ProductEntity p " +
            "JOIN p.categoryList c WHERE c.name = ?1", nativeQuery = true)
    List<ProductEntity> findByCategory(String cateName);

//    @Modifying
//    @Query(value = "INSERT INTO products (id, name, description, price, quantity, image) " +
//            "VALUES (:name, :description, :price, :quantity, CAST(:image AS bytea))", nativeQuery = true)
//    void insertProduct(Long id,
//                       @Param("name") String name,
//                       @Param("description") String description,
//                       @Param("price") Double price,
//                       @Param("quantity") Long quantity,
//                       @Param("image") byte[] image);
}
