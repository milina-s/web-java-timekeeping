package com.example.repositories;

import com.example.entities.Category;
import com.example.repositories.dao.CategoryDao;

import java.util.List;

public class CategoryRepository implements Repository<Category> {
    private final CategoryDao categoryDao;

    public CategoryRepository(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public List<Category> findAll() {
        return categoryDao.findAll();
    }

    @Override
    public Category findById(Long id) {
        return categoryDao.findById(id);
    }

    @Override
    public void create(Category item) {
        categoryDao.create(item);
    }

    @Override
    public void update(Long id, Category newItem) {
        categoryDao.update(id, newItem);
    }

    @Override
    public void delete(Long id) {
        categoryDao.delete(id);
    }

    public List<Category> findByName(String name) {
        return categoryDao.findByName(name);
    }
}
