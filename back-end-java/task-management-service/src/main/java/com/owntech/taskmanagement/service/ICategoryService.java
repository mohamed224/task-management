package com.owntech.taskmanagement.service;

import com.owntech.taskmanagement.dto.CategoryDto;

import java.util.List;

public interface ICategoryService {

    List<CategoryDto> getCategories();

    CategoryDto getCategoryById(Long id);

    CategoryDto saveCategory(CategoryDto categoryDto);

    CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId);

    void deleteCategory(Long id);
}
