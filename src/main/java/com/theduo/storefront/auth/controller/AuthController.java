package com.theduo.storefront.auth.controller;

import com.theduo.storefront.auth.dto.JwtResponse;
import com.theduo.storefront.auth.dto.LoginRequest;
import com.theduo.storefront.auth.dto.RegisterUserRequest;
import com.theduo.storefront.common.config.JwtConfig;
import com.theduo.storefront.user.exception.ExistByEmailException;
import com.theduo.storefront.common.util.JwtService;
import com.theduo.storefront.user.repo.UserRepository;
import com.theduo.storefront.user.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
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
    private final JwtConfig jwtConfig;

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
            @Valid @RequestBody LoginRequest request,
             HttpServletResponse response
            ) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var user = userRepository.findUserByEmail(request.getEmail()).orElseThrow();

        var accessToken = jwtService.generateAccessToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);

        var cookie = new Cookie("refresh-token", refreshToken.toString());
        cookie.setHttpOnly(true);
        cookie.setPath("/auth/refresh");
        cookie.setSecure(true);
        cookie.setMaxAge(jwtConfig.getRefreshTokenExpiration());
        response.addCookie(cookie);

        return ResponseEntity.ok(new JwtResponse(accessToken.toString()));
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtResponse> refresh(
            @CookieValue("refresh-token") String refreshToken
    ) {
        var jwt = jwtService.parseToken(refreshToken);
        if(jwt == null || jwt.isExpired()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        var user = userRepository.findById(jwt.getUserId()).orElseThrow();
        var accessToken = jwtService.generateAccessToken(user);

        return  ResponseEntity.ok(new JwtResponse(accessToken.toString()));
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
