package com.example.services;

import com.example.entities.Category;
import com.example.repositories.CategoryRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CategoryService {

    private static final Logger logger = LogManager.getLogger(CategoryService.class);
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void create(String name) {
        categoryRepository.create(new Category
                .CategoryBuilder()
                .setName(name)
                .build());
    }

    public void delete(Long id) {
        categoryRepository.delete(id);
    }
}
