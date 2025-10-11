package com.theduo.storefront.product.mapper;

import com.theduo.storefront.product.dto.CreateProductRequest;
import com.theduo.storefront.product.dto.ProductDto;
import com.theduo.storefront.product.dto.UpdateProductRequest;
import com.theduo.storefront.product.entity.Product;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category", ignore = true)
    Product toRegisterDto(CreateProductRequest request);

    @Mapping(source = "category.id", target = "categoryId")
    ProductDto toProductDto(Product product);

    @Mapping(target = "category", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void toUpdateDto(UpdateProductRequest request, @MappingTarget Product product);
}