package com.theduo.storefront.cart.service;

import com.theduo.storefront.cart.dto.AddItemToCartRequest;
import com.theduo.storefront.cart.dto.CartDto;
import com.theduo.storefront.cart.dto.CartItemDto;
import com.theduo.storefront.cart.dto.UpdateCartItemRequest;
import com.theduo.storefront.cart.entity.Cart;
import com.theduo.storefront.cart.exception.CartNotFoundException;
import com.theduo.storefront.cart.mapper.CartMapper;
import com.theduo.storefront.cart.repo.CartRepository;
import com.theduo.storefront.product.exception.ProductNotException;
import com.theduo.storefront.product.repo.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final CartMapper cartMapper;
    private final ProductRepository productRepository;

    public CartDto create() {
        var cart = new Cart();
        cartRepository.save(cart);
        return cartMapper.toCartDto(cart);
    }

    public CartDto getCartById(UUID cardId) {
        var cart = cartRepository.getCartsWithItems(cardId)
                .orElseThrow(CartNotFoundException::new);
        return cartMapper.toCartDto(cart);
    }

    public CartItemDto addItem(UUID cartId, AddItemToCartRequest request) {
        var cart = cartRepository.getCartsWithItems(cartId)
                .orElseThrow(CartNotFoundException::new);

        var product = productRepository.findById(request.getProductId())
                .orElseThrow(ProductNotException::new);

        var cartItem = cart.addItem(product);
        cartRepository.save(cart);
        return cartMapper.toCartItemDto(cartItem);
    }

    public CartItemDto updateItem(UUID cartId, Long productId, UpdateCartItemRequest request) {
        var cart = cartRepository.getCartsWithItems(cartId)
                .orElseThrow(CartNotFoundException::new);

        var cartItem = cart.getItem(productId);
        if(cartItem == null) {
            throw new CartNotFoundException();
        }

        cartItem.setQuantity(cartItem.getQuantity() + request.getQuantity());
        cartRepository.save(cart);
        return cartMapper.toCartItemDto(cartItem);
    }

    public void removeItem(UUID cartId, Long productId) {
        var cart = cartRepository.getCartsWithItems(cartId)
                .orElseThrow(CartNotFoundException::new);
        cart.removeItem(productId);
        cartRepository.save(cart);
    }

    public void clear(UUID cartId) {
        var cart = cartRepository.getCartsWithItems(cartId)
                .orElseThrow(CartNotFoundException::new);
       cart.clear();
       cartRepository.save(cart);
    }
}
