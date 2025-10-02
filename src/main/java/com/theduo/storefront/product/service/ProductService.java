package com.theduo.storefront.product.service;

import com.theduo.storefront.category.exception.CategoryNotFoundException;
import com.theduo.storefront.category.repo.CategoryRepository;
import com.theduo.storefront.product.dto.CreateProductRequest;
import com.theduo.storefront.product.dto.ProductDto;
import com.theduo.storefront.product.mapper.ProductMapper;
import com.theduo.storefront.product.repo.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    public ProductDto registerProduct(CreateProductRequest request) {
        var category = categoryRepository.findById(request.getCategoryId()).orElse(null);
        if(category == null) {
            throw new CategoryNotFoundException();
        }

        var product = productMapper.toRegisterDto(request);
        product.setCategory(category);
        productRepository.save(product);

        return productMapper.toProductDto(product);
    }
}
