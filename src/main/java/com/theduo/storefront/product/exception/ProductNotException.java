package com.theduo.storefront.product.exception;

public class ProductNotException extends RuntimeException {
    public ProductNotException() {
        super("Product not found");
    }
}
