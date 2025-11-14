package com.theduo.storefront.order.service;

import com.theduo.storefront.auth.service.AuthService;
import com.theduo.storefront.order.dto.OrderDto;
import com.theduo.storefront.order.exception.AccessDeniedException;
import com.theduo.storefront.order.exception.OrderNotFoundException;
import com.theduo.storefront.order.mapper.OrderMapper;
import com.theduo.storefront.order.repo.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class OrderService {

    private final AuthService authService;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public List<OrderDto> getAllOrders() {
        var user = authService.getCurrentUser();
        var order = orderRepository.getOrdersByCustomer(user);

        return order.stream().map(orderMapper::toOrderDto).toList();
    }

    public OrderDto getOrder(Long orderId) {
        var order = orderRepository.getOrderWithItem(orderId).orElseThrow(
                OrderNotFoundException::new
        );
        var user = authService.getCurrentUser();

        if(!order.isPlacedByCustomer(user)) {
            throw new AccessDeniedException("Access Denied");
        }

        return orderMapper.toOrderDto(order);
    }
}
