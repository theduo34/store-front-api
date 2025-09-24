package com.theduo.storefront.auth.controller;

import com.theduo.storefront.auth.dto.JwtResponse;
import com.theduo.storefront.auth.dto.LoginRequest;
import com.theduo.storefront.auth.dto.RegisterUserRequest;
import com.theduo.storefront.common.exception.ExistByEmailException;
import com.theduo.storefront.common.util.JwtService;
import com.theduo.storefront.user.repo.UserRepository;
import com.theduo.storefront.user.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
@Tag(name = "Auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @Valid @RequestBody RegisterUserRequest request,
            UriComponentsBuilder builder) {
        var userDto = userService.register(request);

        var uri = builder.path("/user/{uuid}").buildAndExpand(userDto.getUuid()).toUri();

        return ResponseEntity.created(uri).body(userDto);

    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(
            @Valid @RequestBody LoginRequest request
            ) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var user = userRepository.findUserByEmail(request.getEmail()).orElseThrow();

        var accessToken = jwtService.generateAccessToken(user);

        return ResponseEntity.ok(new JwtResponse(accessToken.toString()));
    }

    @ExceptionHandler(ExistByEmailException.class)
    public ResponseEntity<Map<String,String>> handleExistByEmailException() {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                Map.of("email", "Email already exists")
        );
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Map<String,String>> handleBadCredentialsException() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                Map.of("error", "Invalid email or password")
        );
    }

}
