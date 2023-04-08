package com.ff.service;

import com.ff.entity.ProductEntity;

public interface ProductService {
    ProductEntity addNewProduct(ProductEntity product);
    ProductEntity removeProduct(String productName);
}
