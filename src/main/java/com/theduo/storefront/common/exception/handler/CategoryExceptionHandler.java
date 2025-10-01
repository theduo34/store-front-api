package com.theduo.storefront.common.exception.handler;

import com.theduo.storefront.category.controller.CategoryController;
import com.theduo.storefront.category.exception.CategoryExistByNameException;
import com.theduo.storefront.category.exception.CategoryNotFoundException;
import com.theduo.storefront.category.exception.CategoryWithAProductException;
import com.theduo.storefront.common.response.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = CategoryController.class)
public class CategoryExceptionHandler {

    @ExceptionHandler(CategoryExistByNameException.class)
    public ResponseEntity<ApiResponse<Void>> handleCategoryExistByNameException(
            HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                ApiResponse.error(
                        "Category already exists",
                        HttpStatus.CONFLICT.value(),
                        request.getRequestURI())
                );
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleCategoryNotFound(
            HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                ApiResponse.error(
                        "Category not found",
                        HttpStatus.NOT_FOUND.value(),
                        request.getRequestURI())
        );
    }

    @ExceptionHandler(CategoryWithAProductException.class)
    public ResponseEntity<ApiResponse<Void>> handleCategoryWithProduct(
            HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                ApiResponse.error(
                        "Cannot delete category with products",
                        HttpStatus.CONFLICT.value(),
                        request.getRequestURI())
        );
    }
}
