package com.ff.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ff.entity.ProductEntity;
import com.ff.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/manageProduct")
public class ManageProductController {
    @Autowired
    ProductService productService;

    private static final ObjectMapper objectMapper = new ObjectMapper();
    @PostMapping("/addNewProduct")
    public ResponseEntity<ProductEntity> addNewProduct(@RequestBody String json) throws JsonProcessingException {
        ProductEntity product = objectMapper.readValue(json, ProductEntity.class);
        return new ResponseEntity<>(productService.addNewProduct(product), HttpStatus.OK);
    }

    @DeleteMapping("/removeProduct/{productName}")
    public ResponseEntity<ProductEntity> removeProduct(@PathVariable("productName") String productName) {
        ProductEntity product = productService.removeProduct(productName);
        if (product != null)
            return new ResponseEntity<>(product, HttpStatus.OK);
        else
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
