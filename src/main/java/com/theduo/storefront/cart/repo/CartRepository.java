package com.theduo.storefront.cart.repo;

import com.theduo.storefront.cart.entity.Cart;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CartRepository extends CrudRepository<Cart, UUID> {
}
