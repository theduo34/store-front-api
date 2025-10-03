package com.theduo.storefront.product.service;

import com.theduo.storefront.category.exception.CategoryNotFoundException;
import com.theduo.storefront.category.repo.CategoryRepository;
import com.theduo.storefront.product.dto.CreateProductRequest;
import com.theduo.storefront.product.dto.ProductDto;
import com.theduo.storefront.product.entity.Product;
import com.theduo.storefront.product.mapper.ProductMapper;
import com.theduo.storefront.product.repo.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<ProductDto> getAllProducts(Byte categoryId) {
        List <Product> products;

        if(categoryId != null) {
            products = productRepository.findByCategoryId(categoryId);
        } else {
            products = productRepository.findAllWithCategory();
        }

        return products.stream().map(productMapper::toProductDto).collect(Collectors.toList());
    }
}
