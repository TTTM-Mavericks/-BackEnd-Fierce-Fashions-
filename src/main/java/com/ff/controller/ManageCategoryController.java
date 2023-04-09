package com.ff.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ff.entity.CategoryEntity;
import com.ff.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/manageCategory")
public class ManageCategoryController {
    @Autowired
    CategoryService categoryService;

    private static final ObjectMapper objectMapper = new ObjectMapper();
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
}
