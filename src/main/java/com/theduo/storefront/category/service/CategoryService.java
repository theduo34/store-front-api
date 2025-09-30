package com.theduo.storefront.category.service;


import com.theduo.storefront.category.dto.CategoryDto;
import com.theduo.storefront.category.dto.CreateCategoryRequest;
import com.theduo.storefront.category.mapper.CategoryMapper;
import com.theduo.storefront.category.repo.CategoryRepository;
import com.theduo.storefront.common.exception.CategoryExistByNameException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryDto createCategory(CreateCategoryRequest  request) {
        if(categoryRepository.existsByName(request.getName())) {
            throw new CategoryExistByNameException();
        }
        var category = categoryMapper.toRegisterDto(request);
        categoryRepository.save(category);

        return categoryMapper.toCategoryDto(category);
    }

    public CategoryDto updateCategory(int categoryId,CreateCategoryRequest request) {
        var category = categoryRepository.findById((byte) categoryId).orElse(null);
        if(category == null) {
             throw new CategoryExistByNameException();
        }

        category.setName(request.getName());
        categoryRepository.save(category);

        return categoryMapper.toCategoryDto(category);
    }
}
