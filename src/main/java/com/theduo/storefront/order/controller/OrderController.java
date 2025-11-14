package com.theduo.storefront.order.controller;

import com.theduo.storefront.common.response.SuccessResponse;
import com.theduo.storefront.order.dto.OrderDto;
import com.theduo.storefront.order.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/order")
@Tag(name = "Orders", description = "Manage customer orders")
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    @Operation(summary = "Get All Orders")
    public ResponseEntity<SuccessResponse<List<OrderDto>>> getAllOrders(
            HttpServletRequest ex
    ) {
        var orders = orderService.getAllOrders();

        return ResponseEntity.ok(SuccessResponse.of(
                orders, "Order retrieved successfully",
                HttpStatus.OK.value(), ex.getRequestURI()
        ));
    }

    @GetMapping("/{orderId}")
    @Operation(summary = "Get a Customer order")
    public ResponseEntity<SuccessResponse<OrderDto>> getOrder(
            @PathVariable Long orderId,
            HttpServletRequest ex
    ){
        var order = orderService.getOrder(orderId);
        return ResponseEntity.ok(SuccessResponse.of(
                order, "Order retrieved successfully",
                HttpStatus.OK.value(), ex.getRequestURI()
        ));
    }
}
