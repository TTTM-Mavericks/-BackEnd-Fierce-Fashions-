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
        return categoryRepository.save(category);
    }

    @Override
    public CategoryEntity removeCategory(String name) {
        CategoryEntity category = categoryRepository.findByname(name);
        if (category == null)
            return null;
        else {
            categoryRepository.delete(category);
            return category;
        }
    }
}
