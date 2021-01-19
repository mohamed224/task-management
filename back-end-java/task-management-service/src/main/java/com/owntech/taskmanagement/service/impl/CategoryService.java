package com.owntech.taskmanagement.service.impl;

import com.owntech.taskmanagement.dao.CategoryDao;
import com.owntech.taskmanagement.entities.Category;
import com.owntech.taskmanagement.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService extends GenericService<Category, Long> implements ICategoryService {
    private CategoryDao categoryDao;

    @Autowired
    public CategoryService(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public List<Category> getCategories() {
        return categoryDao.findAll();
    }

    @Override
    public Category getCategoryById(Long id) {
        Optional<Category> optionalCategory = categoryDao.findById(id);
        if (optionalCategory.isPresent()) {
            return optionalCategory.get();
        }
        throw new RuntimeException("Category doesn't exist");
    }

    @Override
    public Category saveCategory(Category category) {
        return categoryDao.saveAndFlush(category);
    }

    @Override
    public Category updateCategory(Category category, Long categoryId) {
        category.setId(categoryId);
        return categoryDao.saveAndFlush(category);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryDao.delete(id);
    }
}
