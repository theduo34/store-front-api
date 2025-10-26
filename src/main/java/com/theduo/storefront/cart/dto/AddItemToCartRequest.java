package com.theduo.storefront.cart.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddItemToCartRequest {
    @NotNull(message = "Product ID is required")
    private Long productId;
}
