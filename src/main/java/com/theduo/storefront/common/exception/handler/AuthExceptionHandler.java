package com.theduo.storefront.common.exception.handler;

import com.theduo.storefront.auth.controller.AuthController;
import com.theduo.storefront.category.controller.CategoryController;
import com.theduo.storefront.common.response.ApiResponse;
import com.theduo.storefront.user.exception.ExistByEmailException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = AuthController.class)
public class AuthExceptionHandler {

    @ExceptionHandler(ExistByEmailException.class)
    public ResponseEntity<ApiResponse<Void>> handleExistByEmail(
            ExistByEmailException ex,
            HttpServletRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                ApiResponse.error(
                        ex.getMessage(),
                        HttpStatus.CONFLICT.value(),
                        request.getRequestURI()
                )
        );
    }

}
