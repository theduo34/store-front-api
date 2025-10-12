package com.theduo.storefront.cart.service;

import com.theduo.storefront.cart.dto.CartDto;
import com.theduo.storefront.cart.entity.Cart;
import com.theduo.storefront.cart.mapper.CartMapper;
import com.theduo.storefront.cart.repo.CartRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final CartMapper cartMapper;

    public CartDto create() {
        var cart = new Cart();

        cartRepository.save(cart);

        return cartMapper.toCartDto(cart);
    }
}
