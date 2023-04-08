package com.ff.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ff.entity.CategoryEntity;
import com.ff.entity.OrderEntity;
import com.ff.entity.ProductEntity;
import com.ff.entity.UserEntity;
import com.ff.service.AdminService;
import com.ff.service.CategoryService;
import com.ff.service.OrderService;
import com.ff.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminService adminService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    OrderService orderService;
    @Autowired
    ProductService productService;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("/getAllCustomer")
    public ResponseEntity<List<UserEntity>> getAllCustomer() {
        return new ResponseEntity<>(adminService.getAllCustomer(), HttpStatus.OK);
    }

    @GetMapping("/banUser/{username}")
    public ResponseEntity<UserEntity> banUser(@PathVariable("username") String username) {
        return new ResponseEntity<>(adminService.banUser(username), HttpStatus.OK);
    }

    @GetMapping("/unbanUser/{username}")
    public ResponseEntity<UserEntity> unbanUser(@PathVariable("username") String username) {
        return new ResponseEntity<>(adminService.unbanUser(username), HttpStatus.OK);
    }

    @PostMapping("/addNewCategory")
    public ResponseEntity<CategoryEntity> addNewCategory(@RequestBody String json) throws JsonProcessingException {
        CategoryEntity category = objectMapper.readValue(json, CategoryEntity.class);
        return new ResponseEntity<>(categoryService.addNewCategory(category), HttpStatus.OK);
    }

    @DeleteMapping("/removeCategory/{cateName}")
    public ResponseEntity<CategoryEntity> removeCategory(@PathVariable("cateName") String cateName) {
        CategoryEntity category = categoryService.removeCategory(cateName);
        if (category != null)
            return new ResponseEntity<>(category, HttpStatus.OK);
        else
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

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

    @GetMapping("/viewAllOrder")
    public ResponseEntity<List<OrderEntity>> vielAllOrder() {
        List<OrderEntity> listOrder = orderService.viewAllOrder();
        return new ResponseEntity<>(listOrder, HttpStatus.OK);
    }


    @GetMapping("/changeOrderStatus")
    public ResponseEntity<OrderEntity> changeStatus(@RequestBody String json) throws JsonProcessingException {
        ChangeStatusRequest request = objectMapper.readValue(json, ChangeStatusRequest.class);
        if(request == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        OrderEntity order = orderService.changeStatus(request.getId_order(), request.getOrderStatus());
        if(order != null){
            return new ResponseEntity<>(order, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
class ChangeStatusRequest{
    private Long id_order;
    private String orderStatus;
    // Completed
    // Processed
    // Cancelled

    public Long getId_order() {
        return id_order;
    }

    public String getOrderStatus() {
        return orderStatus;
    }
}
