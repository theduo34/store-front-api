package com.theduo.storefront.common.exception.handler;

import com.theduo.storefront.common.response.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleValidationErrors(
            MethodArgumentNotValidException exception,
            HttpServletRequest request
    ) {
        // Get first validation error
        var error = exception.getBindingResult().getFieldErrors().getFirst();
        String field = error.getField();
        String message = error.getDefaultMessage();

        ApiResponse<Void> response = ApiResponse.error(
                String.format("Validation failed: %s %s", field, message),
                HttpStatus.BAD_REQUEST.value(),
                request.getRequestURI()
        );

        return ResponseEntity.badRequest().body(response);
    }
}
