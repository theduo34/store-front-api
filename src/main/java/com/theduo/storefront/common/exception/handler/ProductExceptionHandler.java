package com.theduo.storefront.common.exception.handler;


import com.theduo.storefront.category.exception.CategoryNotFoundException;
import com.theduo.storefront.common.response.ErrorResponse;
import com.theduo.storefront.product.controller.ProductController;
import com.theduo.storefront.product.exception.ProductNotException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = {ProductController.class})
public class ProductExceptionHandler {
    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCategoryNotFoundException(
            CategoryNotFoundException e, HttpServletRequest request
    ) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                ErrorResponse.of(e.getMessage(),
                        HttpStatus.NOT_FOUND.value(),
                        request.getRequestURI()
                )
        );
    }

    @ExceptionHandler(ProductNotException.class)
    public ResponseEntity<ErrorResponse> handleProductNotFoundException(
            ProductNotException e, HttpServletRequest request
    ) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                ErrorResponse.of(e.getMessage(),
                        HttpStatus.NOT_FOUND.value(),
                        request.getRequestURI()
                )
        );
    }
}
