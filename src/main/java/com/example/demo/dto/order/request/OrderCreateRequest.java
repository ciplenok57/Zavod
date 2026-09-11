package com.example.demo.dto.order.request;

import com.example.demo.dto.order_item.request.OrderItemRequest;

import java.time.LocalDate;
import java.util.List;

public record OrderCreateRequest(
        String name,
        String client,
        LocalDate deadline,
        String comment,
        List<OrderItemRequest> items
) {
    public OrderCreateRequest {
        if (items == null) items = List.of();
    }
}