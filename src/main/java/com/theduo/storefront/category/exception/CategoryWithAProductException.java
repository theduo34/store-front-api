package com.theduo.storefront.category.exception;

public class CategoryWithAProductException extends RuntimeException {
    public CategoryWithAProductException() {
        super("CCannot delete category with product");
    }
}
