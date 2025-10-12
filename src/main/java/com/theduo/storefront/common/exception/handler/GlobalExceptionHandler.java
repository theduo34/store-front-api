package com.theduo.storefront.common.exception.handler;

import com.theduo.storefront.common.response.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationErrors(
            MethodArgumentNotValidException exception,
            HttpServletRequest request
    ) {
        // Get first validation error
        var error = exception.getBindingResult().getFieldErrors().getFirst();

        return ResponseEntity.badRequest().body(
                ErrorResponse.of(
                        String.format(error.getField() + ": %s", error.getDefaultMessage()),
                        HttpStatus.BAD_REQUEST.value(),
                        request.getRequestURI()
                )
        );
    }
}
