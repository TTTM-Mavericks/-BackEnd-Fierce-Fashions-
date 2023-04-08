package com.ff.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ff.entity.CategoryEntity;
import com.ff.entity.UserEntity;
import com.ff.service.AdminService;
import com.ff.service.CategoryService;
import jakarta.websocket.server.PathParam;
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

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("/getAllCustomer")
    public ResponseEntity<List<UserEntity>> getAllCustomer(){
        return new ResponseEntity<>(adminService.getAllCustomer(), HttpStatus.OK);
    }

    @GetMapping("/banUser/{username}")
    public ResponseEntity<UserEntity> banUser(@PathParam("username") String username){
        return new ResponseEntity<>(adminService.banUser(username), HttpStatus.OK);
    }

    @GetMapping("/unbanUser/{username}")
    public ResponseEntity<UserEntity> unbanUser(@PathParam("username") String username){
        return new ResponseEntity<>(adminService.unbanUser(username), HttpStatus.OK);
    }

    @PostMapping("/addNewCategory")
    public ResponseEntity<CategoryEntity> addNewCategory(@RequestBody String json) throws JsonProcessingException {
        CategoryEntity category = objectMapper.readValue(json, CategoryEntity.class);
        return new ResponseEntity<>(categoryService.addNewCategory(category), HttpStatus.OK);
    }
}
