package com.ff.service;

import com.ff.entity.ProductEntity;

import java.util.List;

public interface ProductService {
    ProductEntity addNewProduct(ProductEntity product);
    ProductEntity removeProduct(String productName);
    List<ProductEntity> listAllProduct();
    ProductEntity findByName(String name);
    List<ProductEntity> findByCategory(String categoryName);

}
