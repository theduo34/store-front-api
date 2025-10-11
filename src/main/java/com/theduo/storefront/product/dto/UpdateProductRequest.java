package com.theduo.storefront.product.dto;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateProductRequest {
    @Size(min = 2, max = 255, message = "Name must be between 2 to 255 characters long")
    private String name;

    @Size(min = 8, max = 255, message = "Description must be between 8 to 255 characters")
    private String description;

    @Positive(message = "Price must be greater than 0")
    private Double price;

    private Byte categoryId;
}