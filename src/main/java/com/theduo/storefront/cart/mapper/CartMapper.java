package com.theduo.storefront.cart.mapper;

import com.theduo.storefront.cart.dto.CartDto;
import com.theduo.storefront.cart.dto.CartItemDto;
import com.theduo.storefront.cart.entity.Cart;
import com.theduo.storefront.cart.entity.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartMapper {
    @Mapping(target="items", source="items")
    @Mapping(target="totalPrice", expression = "java(cart.getTotalPrice())")
    CartDto toCartDto(Cart cart);

    @Mapping(target = "totalPrice", expression = "java(cartItem.getTotalPrice())")
    CartItemDto toCartItemDto(CartItem cartItem);
}
