package com.theduo.storefront.common.exception.handler;

import com.theduo.storefront.category.controller.CategoryController;
import com.theduo.storefront.category.exception.CategoryExistByNameException;
import com.theduo.storefront.category.exception.CategoryNotFoundException;
import com.theduo.storefront.category.exception.CategoryWithAProductException;
import com.theduo.storefront.common.response.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = CategoryController.class)
public class CategoryExceptionHandler {

    @ExceptionHandler(CategoryExistByNameException.class)
    public ResponseEntity<ErrorResponse> handleCategoryExistByNameException(
            CategoryExistByNameException ex, HttpServletRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                ErrorResponse.of(ex.getMessage(),
                        HttpStatus.CONFLICT.value(),
                        request.getRequestURI()
                )
        );
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCategoryNotFound(
            CategoryNotFoundException ex, HttpServletRequest request
    ) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                ErrorResponse.of(ex.getMessage(),
                        HttpStatus.NOT_FOUND.value(),
                        request.getRequestURI()
                )
        );
    }

    @ExceptionHandler(CategoryWithAProductException.class)
    public ResponseEntity<ErrorResponse> handleCategoryWithProduct(
            CategoryWithAProductException ex, HttpServletRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                ErrorResponse.of(ex.getMessage(),
                        HttpStatus.CONFLICT.value(),
                        request.getRequestURI()
                )
        );
    }
}
