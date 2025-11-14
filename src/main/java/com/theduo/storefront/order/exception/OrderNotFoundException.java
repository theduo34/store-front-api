package com.theduo.storefront.order.exception;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException() {
        super("Order Not Found");
    }
}
