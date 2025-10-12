package com.theduo.storefront.cart.controller;

import com.theduo.storefront.cart.dto.CartDto;
import com.theduo.storefront.cart.service.CartService;
import com.theduo.storefront.common.response.SuccessResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@AllArgsConstructor
@Tag(name = "Cart")
@RestController
@RequestMapping("/cart")
public class CartController {
    private CartService cartService;

    @PostMapping
    public ResponseEntity<SuccessResponse<CartDto>> createCart(
            HttpServletRequest request,
            UriComponentsBuilder builder
    ) {
        var cartDto = cartService.create();

        var uri = builder.path("/cart/{id}").buildAndExpand(cartDto.getId()).toUri();

        return ResponseEntity.created(uri).body(
                SuccessResponse.of(
                        cartDto, "Cart created successfully",
                        HttpStatus.CREATED.value(),request.getRequestURI()
                )
        );
    }
}
