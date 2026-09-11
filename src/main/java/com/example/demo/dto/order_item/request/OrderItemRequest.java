package com.example.demo.dto.order_item.request;

import com.example.demo.entity.jsonb.OrderItemFieldValue;

import java.util.List;
import java.util.UUID;

public record OrderItemRequest(
        UUID templateId,
        List<OrderItemFieldValue> fieldValues
) {
    public OrderItemRequest {
        if (fieldValues == null) fieldValues = List.of();
    }
}