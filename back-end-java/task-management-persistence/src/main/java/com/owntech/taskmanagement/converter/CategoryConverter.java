package com.owntech.taskmanagement.converter;

import com.owntech.taskmanagement.dto.CategoryDto;
import com.owntech.taskmanagement.entities.Category;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class CategoryConverter {
    private CategoryConverter() {
    }

    public static CategoryDto modelToDto(Category category) {
        return new CategoryDto(category.getId(), category.getName(), category.getDescription());
    }

    public static Category dtoToModel(CategoryDto categoryDto) {
        return new Category(categoryDto.getId(), categoryDto.getName(), categoryDto.getDescription(), false);
    }

    public static List<CategoryDto> modelsToDtos(Collection<Category> categories) {
        return categories.stream().filter(Objects::nonNull).map(CategoryConverter::modelToDto).collect(Collectors.toList());
    }

}
