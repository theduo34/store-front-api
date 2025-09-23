package com.theduo.storefront.common.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(
            MethodArgumentNotValidException exception) {

        var error = exception.getBindingResult().getFieldErrors().getFirst();

        assert error.getDefaultMessage() != null;
        Map<String, String> response = Map.of(
                error.getField(),
                error.getDefaultMessage()
        );

        return ResponseEntity.badRequest().body(response);
    }
}

