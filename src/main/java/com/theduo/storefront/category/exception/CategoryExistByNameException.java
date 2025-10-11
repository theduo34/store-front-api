package com.theduo.storefront.category.exception;

public class CategoryExistByNameException extends RuntimeException {
    public CategoryExistByNameException() {
        super("Category already exists");
    }
}
