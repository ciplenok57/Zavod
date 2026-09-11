package com.example.demo.dto.order.response;

import com.example.demo.dto.order_item.response.OrderItemResponseDto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record OrderResponseDto(
        UUID id,
        String name,
        String client,
        LocalDate deadline,
        String comment,
        List<OrderItemResponseDto> items
) {
    public OrderResponseDto {
        if (items == null) items = List.of();
    }
}