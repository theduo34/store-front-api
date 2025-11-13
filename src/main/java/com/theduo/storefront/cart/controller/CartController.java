package com.theduo.storefront.cart.controller;

import com.theduo.storefront.cart.dto.AddItemToCartRequest;
import com.theduo.storefront.cart.dto.CartDto;
import com.theduo.storefront.cart.dto.CartItemDto;
import com.theduo.storefront.cart.dto.UpdateCartItemRequest;
import com.theduo.storefront.cart.service.CartService;
import com.theduo.storefront.common.response.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@AllArgsConstructor
@Tag(name = "Cart", description = "Manage shopping carts and cart items")
@RestController
@RequestMapping("/carts")
public class CartController {
    private CartService cartService;

    @PostMapping
    @Operation(summary = "Create New Cart")
    public ResponseEntity<SuccessResponse<CartDto>> createCart(
            HttpServletRequest request,
            UriComponentsBuilder builder
    ) {
        var cartDto = cartService.create();
        var uri = builder.path("/carts/{id}").buildAndExpand(cartDto.getId()).toUri();

        return ResponseEntity.created(uri).body(
                SuccessResponse.of(
                        cartDto, "Cart created successfully",
                        HttpStatus.CREATED.value(),request.getRequestURI()
                )
        );
    }

    @GetMapping("/{cartId}")
    @Operation(summary = "Get Cart Details")
    public CartDto getCart(@PathVariable UUID cartId) {
       return cartService.getCartById(cartId);
    }

    @PostMapping("/{cartId}/items")
    @Operation(summary = "Add an Item to Cart")
    public ResponseEntity<SuccessResponse<CartItemDto>> addCartItem(
            @PathVariable UUID cartId,
            @Valid @RequestBody AddItemToCartRequest request,
            HttpServletRequest ex,
            UriComponentsBuilder builder
    ) {
        var cartItem = cartService.addItem(cartId, request);
        var uri = builder.path("/carts/{cartId}/items").buildAndExpand(cartId).toUri();

        return ResponseEntity.created(uri).body(
                SuccessResponse.of(cartItem, "Item added to cart successfully",
                        HttpStatus.CREATED.value(), ex.getRequestURI())
        );
    }

    @PatchMapping("/{cartId}/items/{productId}")
    @Operation(summary = "Update Cart Item Quantity")
    public ResponseEntity<SuccessResponse<CartItemDto>> updateCartItem(
            @PathVariable UUID cartId,
            @PathVariable Long productId,
            @Valid @RequestBody UpdateCartItemRequest request,
            HttpServletRequest ex
    ) {
        var cartItem = cartService.updateItem(cartId, productId, request);

        return ResponseEntity.ok().body(
                SuccessResponse.of(
                        cartItem, "Item quantity updated successfully",
                        HttpStatus.OK.value(), ex.getRequestURI()
                )
        );
    }

    @DeleteMapping("/{cartId}/items/{productId}")
    @Operation(summary = "Remove an Item from Cart")
    public ResponseEntity<SuccessResponse<Void>> deleteCartItem(
            @PathVariable UUID cartId,
            @PathVariable Long productId,
            HttpServletRequest ex
    ) {
        cartService.removeItem(cartId, productId);
        return ResponseEntity.ok(SuccessResponse.of(
                null,"Item removed from cart successfully",
                HttpStatus.OK.value(), ex.getRequestURI()
        ));
    }

    @DeleteMapping("/{cartId}/items")
    @Operation(summary = "Remove All Items from Cart")
    public ResponseEntity<SuccessResponse<Void>> clearCart(
            @PathVariable UUID cartId,
            HttpServletRequest ex
    ) {
        cartService.clear(cartId);
        return ResponseEntity.ok(SuccessResponse.of(
                null, "Cart cleared successfully",
                HttpStatus.OK.value(), ex.getRequestURI()
        ));
    }


}
