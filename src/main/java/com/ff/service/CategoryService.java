package com.ff.service;

import com.ff.entity.CategoryEntity;

public interface CategoryService {
    CategoryEntity addNewCategory(CategoryEntity category);
    CategoryEntity removeCategory(String name);
}
