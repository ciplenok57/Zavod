package com.example.demo.service;

import com.example.demo.dto.order.request.OrderCreateRequest;
import com.example.demo.dto.order.request.OrderUpdateRequest;
import com.example.demo.dto.order.response.OrderResponseDto;
import com.example.demo.dto.order.response.OrderShortResponseDto;
import com.example.demo.dto.order_item.request.OrderItemRequest;
import com.example.demo.dto.order_item.response.OrderItemResponseDto;

import java.util.List;
import java.util.UUID;

public interface OrderService {

    OrderResponseDto create(OrderCreateRequest request);

    List<OrderShortResponseDto> getAll();

    OrderResponseDto getById(UUID id);

    List<OrderShortResponseDto> search(String query);

    List<OrderShortResponseDto> getByClient(String client);

    List<OrderShortResponseDto> getByName(String name);

    OrderResponseDto update(UUID id, OrderUpdateRequest request);

    void delete(UUID id);

    boolean exists(UUID id);

    long count();

    // ===== РАБОТА С ИЗДЕЛИЯМИ =====

    OrderResponseDto addItem(UUID orderId, OrderItemRequest itemRequest);

    OrderResponseDto removeItem(UUID orderId, UUID itemId);

    List<OrderItemResponseDto> getItems(UUID orderId);

    OrderItemResponseDto getItem(UUID orderId, UUID itemId);

    OrderResponseDto updateItem(UUID orderId, UUID itemId, OrderItemRequest itemRequest);

}
