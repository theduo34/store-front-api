package com.theduo.storefront.category.service;


import com.theduo.storefront.category.dto.CategoryDto;
import com.theduo.storefront.category.dto.CreateCategoryRequest;
import com.theduo.storefront.category.mapper.CategoryMapper;
import com.theduo.storefront.category.repo.CategoryRepository;
import com.theduo.storefront.category.exception.CategoryExistByNameException;
import com.theduo.storefront.category.exception.CategoryNotFoundException;
import com.theduo.storefront.category.exception.CategoryWithAProductException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryDto createCategory(CreateCategoryRequest request) {
        if (categoryRepository.existsByName(request.getName())) {
            throw new CategoryExistByNameException();
        }
        var category = categoryMapper.toRegisterDto(request);
        categoryRepository.save(category);

        return categoryMapper.toCategoryDto(category);
    }

    public CategoryDto updateCategory(int categoryId, CreateCategoryRequest request) {
        var category = categoryRepository.findById((byte) categoryId)
                .orElseThrow(CategoryNotFoundException::new);

        if (categoryRepository.existsByName(request.getName())) {
            throw new CategoryExistByNameException();
        }

        category.setName(request.getName());
        var savedCategory = categoryRepository.save(category);

        return categoryMapper.toCategoryDto(savedCategory);
    }

    public CategoryDto getCategoryById(int categoryId) {
        var category = categoryRepository.findById((byte) categoryId)
                .orElseThrow(CategoryNotFoundException::new);

        return categoryMapper.toCategoryDto(category);
    }

    public List<CategoryDto> getAllCategories() {
        var categories = categoryRepository.findAll();

        List<CategoryDto> dtoList = new ArrayList<>();
        for (var category : categories) {
            dtoList.add(categoryMapper.toCategoryDto(category));
        }

        return dtoList;
    }

    public void deleteCategoryById(int categoryId) {
        var category = categoryRepository.findById((byte) categoryId)
                .orElseThrow(CategoryNotFoundException::new);

        if(!category.getProducts().isEmpty()) {
           throw new CategoryWithAProductException();
        }

        categoryRepository.delete(category);
    }

}
