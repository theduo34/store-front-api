package com.theduo.storefront.product.service;

import com.theduo.storefront.category.exception.CategoryNotFoundException;
import com.theduo.storefront.category.repo.CategoryRepository;
import com.theduo.storefront.product.dto.CreateProductRequest;
import com.theduo.storefront.product.dto.ProductDto;
import com.theduo.storefront.product.dto.UpdateProductRequest;
import com.theduo.storefront.product.entity.Product;
import com.theduo.storefront.product.exception.ProductNotException;
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

    // Add new product
    public ProductDto registerProduct(CreateProductRequest request) {
        var category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow((CategoryNotFoundException::new));

        var product = productMapper.toRegisterDto(request);
        product.setCategory(category);
        productRepository.save(product);

        return productMapper.toProductDto(product);
    }

    // Fetch all created products
    public List<ProductDto> getAllProducts(Byte categoryId) {
        List <Product> products;

        if(categoryId != null) {
            products = productRepository.findByCategoryId(categoryId);
        } else {
            products = productRepository.findAllWithCategory();
        }

        return products.stream().map(productMapper::toProductDto).collect(Collectors.toList());
    }

    // Fetch a single created product using it product id
    public ProductDto getProductById(Long productId) {
        var product = productRepository.findById(productId)
                .orElseThrow((ProductNotException::new));

        return productMapper.toProductDto(product);
    }

    public ProductDto updateProduct(Long productId, UpdateProductRequest request) {
        var product = productRepository.findById(productId)
                .orElseThrow(ProductNotException::new);

        if (request.getCategoryId() != null) {
            var category = categoryRepository.findById(request.getCategoryId())
                    .orElseThrow(CategoryNotFoundException::new);
            product.setCategory(category);
        }

        productMapper.toUpdateDto(request, product);

        var savedProduct = productRepository.save(product);
        return productMapper.toProductDto(savedProduct);
    }

    // Delete a product
    public void deleteProduct(Long productId) {
        var product = productRepository.findById(productId)
                .orElseThrow(ProductNotException::new);

        productRepository.delete(product);
    }
}
