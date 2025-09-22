package com.theduo.storefront.auth.controller;

import com.theduo.storefront.auth.dto.RegisterUserRequest;
import com.theduo.storefront.common.exception.ExistByEmailException;
import com.theduo.storefront.user.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
@Tag(name = "Auth")
public class AuthController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @Valid @RequestBody RegisterUserRequest request,
            UriComponentsBuilder builder) {
        var userDto = userService.register(request);

        var uri = builder.path("/user/{uuid}").buildAndExpand(userDto.getUuid()).toUri();

        return ResponseEntity.created(uri).body(userDto);

    }

    @ExceptionHandler(ExistByEmailException.class)
    public ResponseEntity<Map<String,String>> handleExistByEmailException() {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                Map.of("email", "Email already exists")
        );
    }

}
