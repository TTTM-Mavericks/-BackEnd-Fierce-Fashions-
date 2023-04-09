package com.ff.controller;

import com.ff.entity.ProductEntity;
import com.ff.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;
    @GetMapping("/listAllProduct")
    public ResponseEntity<List<ProductEntity>> listAllProduct(){
        List<ProductEntity> listProduct;
        listProduct = productService.listAllProduct();
        return new ResponseEntity<>(listProduct, HttpStatus.OK);
    }

    @GetMapping("/findByName")
    public ResponseEntity<ProductEntity> findByName(@RequestBody String name){
        ProductEntity product;
        product = productService.findByName(name);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/findByCategory")
    public ResponseEntity<List<ProductEntity>> findByCategory(@RequestBody String categoryName){
        List<ProductEntity> listProduct;
        listProduct = productService.findByCategory(categoryName);
        return new ResponseEntity<>(listProduct, HttpStatus.OK);
    }
}
