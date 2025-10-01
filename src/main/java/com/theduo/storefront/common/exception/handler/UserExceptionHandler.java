package com.theduo.storefront.common.exception.handler;

import com.theduo.storefront.common.response.ApiResponse;
import com.theduo.storefront.user.controller.UserController;
import com.theduo.storefront.user.exception.ExistByEmailException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = UserController.class)
public class UserExceptionHandler {

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
