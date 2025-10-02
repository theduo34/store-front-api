package com.theduo.storefront.product.mapper;

import com.theduo.storefront.product.dto.CreateProductRequest;
import com.theduo.storefront.product.dto.ProductDto;
import com.theduo.storefront.product.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDto toProductDto(Product product);
    Product toRegisterDto(CreateProductRequest request);
}
