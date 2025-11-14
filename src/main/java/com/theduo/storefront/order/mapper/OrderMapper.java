package com.theduo.storefront.order.mapper;

import ch.qos.logback.core.model.ComponentModel;
import com.theduo.storefront.order.dto.OrderDto;
import com.theduo.storefront.order.entity.Order;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderDto toOrderDto(Order order);
}
