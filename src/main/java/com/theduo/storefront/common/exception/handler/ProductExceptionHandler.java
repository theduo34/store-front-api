package com.theduo.storefront.common.exception.handler;


import com.theduo.storefront.category.exception.CategoryNotFoundException;
import com.theduo.storefront.common.response.ApiResponse;
import com.theduo.storefront.product.controller.ProductController;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = {ProductController.class})
public class ProductExceptionHandler {

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleCategoryNotFoundException(
            CategoryNotFoundException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                ApiResponse.error(
                        "Category not found",
                        HttpStatus.NOT_FOUND.value(),
                        request.getRequestURI()
                )
        );
    }
}
