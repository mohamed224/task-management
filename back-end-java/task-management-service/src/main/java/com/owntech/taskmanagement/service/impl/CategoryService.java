package com.owntech.taskmanagement.service.impl;

import com.owntech.taskmanagement.converter.CategoryConverter;
import com.owntech.taskmanagement.dao.CategoryDao;
import com.owntech.taskmanagement.dto.CategoryDto;
import com.owntech.taskmanagement.entities.Category;
import com.owntech.taskmanagement.exceptions.ApiErrors;
import com.owntech.taskmanagement.exceptions.HttpCustomException;
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
    public List<CategoryDto> getCategories() {
        return CategoryConverter.modelsToDtos(categoryDao.findAll());
    }

    @Override
    public CategoryDto getCategoryById(Long id) {
        Optional<Category> optionalCategory = categoryDao.findById(id);
        if (optionalCategory.isPresent()) {
            return CategoryConverter.modelToDto(optionalCategory.get());
        }
        throw new HttpCustomException(ApiErrors.OBJECT_NOT_FOUND_STATUS_CODE, String.format(ApiErrors.OBJECT_NOT_FOUND_MESSAGE, "Category"));
    }

    @Override
    public CategoryDto saveCategory(CategoryDto categoryDto) {
        return CategoryConverter.modelToDto(categoryDao.saveAndFlush(CategoryConverter.dtoToModel(categoryDto)));
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId) {
        categoryDto.setId(categoryId);
        return CategoryConverter.modelToDto(categoryDao.saveAndFlush(CategoryConverter.dtoToModel(categoryDto)));
    }

    @Override
    public void deleteCategory(Long id) {
        categoryDao.delete(id);
    }
}
