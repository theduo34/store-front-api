package com.theduo.storefront.cart.repo;

import com.theduo.storefront.cart.entity.CartItem;
import org.springframework.data.repository.CrudRepository;

public interface CartItemRepository extends CrudRepository<CartItem, Long> {
}
