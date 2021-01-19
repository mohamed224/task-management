package com.owntech.taskmanagement.service;

import com.owntech.taskmanagement.entities.Category;

import java.util.List;

public interface ICategoryService {

    List<Category> getCategories();

    Category getCategoryById(Long id);

    Category saveCategory(Category category);

    Category updateCategory(Category category, Long categoryId);

    void deleteCategory(Long id);
}
