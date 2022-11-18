package com.example.services;

import com.example.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RequiredArgsConstructor
public class CategoryService implements Service {

    private static final Logger logger = LogManager.getLogger(CategoryService.class);
    private final CategoryRepository categoryRepository;

    public void delete(Long id) {
        categoryRepository.delete(id);
    }
}
