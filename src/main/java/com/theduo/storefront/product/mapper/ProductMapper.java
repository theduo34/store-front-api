package com.theduo.storefront.product.mapper;

import com.theduo.storefront.product.dto.CreateProductRequest;
import com.theduo.storefront.product.dto.ProductDto;
import com.theduo.storefront.product.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(source = "category.id", target = "categoryId")
    ProductDto toProductDto(Product product);

    Product toRegisterDto(CreateProductRequest request);
}
