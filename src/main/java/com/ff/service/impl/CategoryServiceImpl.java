package com.ff.service.impl;

import com.ff.entity.CategoryEntity;
import com.ff.repository.CategoryRepository;
import com.ff.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public CategoryEntity addNewCategory(CategoryEntity category) {
        CategoryEntity checkCate = categoryRepository.findByName(category.getName());
        if (checkCate == null)
            return categoryRepository.save(category);
        else
            return null;
    }

    @Override
    public CategoryEntity removeCategory(String name) {
        CategoryEntity category = categoryRepository.findByName(name);
        if (category == null)
            return null;
        else {
            categoryRepository.delete(category);
            return category;
        }
    }
}
