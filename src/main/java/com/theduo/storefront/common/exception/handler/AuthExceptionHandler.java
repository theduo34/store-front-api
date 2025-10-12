package com.theduo.storefront.common.exception.handler;

import com.theduo.storefront.auth.controller.AuthController;
import com.theduo.storefront.common.response.ErrorResponse;
import com.theduo.storefront.user.exception.ExistByEmailException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = AuthController.class)
public class AuthExceptionHandler {

    @ExceptionHandler(ExistByEmailException.class)
    public ResponseEntity<ErrorResponse> handleExistByEmail(
            ExistByEmailException ex,
            HttpServletRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                ErrorResponse.of(
                        ex.getMessage(),
                        HttpStatus.CONFLICT.value(),
                        request.getRequestURI()
                )
        );
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleBadCredentialsException(
            BadCredentialsException ex,
            HttpServletRequest request
    ) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                ErrorResponse.of(
                        "Invalid email or password",
                        HttpStatus.UNAUTHORIZED.value(),
                        request.getRequestURI()
                )
        );
    }

}
