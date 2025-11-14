package com.theduo.storefront.order.repo;

import com.theduo.storefront.order.entity.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemsRepository extends JpaRepository<OrderItems, Long> {
}
