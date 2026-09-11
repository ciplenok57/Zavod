package com.example.demo.dto.order_item.response;


import com.example.demo.entity.jsonb.OrderItemFieldValue;
import com.example.demo.entity.jsonb.OrderItemOperation;

import java.util.List;
import java.util.UUID;

public record OrderItemResponseDto(
        UUID id,
        UUID templateId,
        List<OrderItemFieldValue> fieldValues,
        List<OrderItemOperation> operations
) {
    public OrderItemResponseDto {
        if (fieldValues == null) fieldValues = List.of();
        if (operations == null) operations = List.of();
    }
}