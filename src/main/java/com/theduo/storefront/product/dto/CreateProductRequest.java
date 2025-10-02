package com.theduo.storefront.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class CreateProductRequest {
    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 255, message = "Name must be between 2 to 255 characters long")
    private String name;

    @NotBlank(message = "Description is required")
    @Size(min = 8, max = 255, message = "Description must be between 2 t0 255 characters")
    private String description;

    @NotNull(message = "Price cannot be null")
    @Positive(message = "Price must be greater than 0")
    private Double price;

    @NotNull(message = "Category cannot be null")
    private Byte categoryId;
}
