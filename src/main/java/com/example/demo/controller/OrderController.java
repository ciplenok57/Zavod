package com.example.demo.controller;

import com.example.demo.dto.order.request.OrderCreateRequest;
import com.example.demo.dto.order.request.OrderUpdateRequest;
import com.example.demo.dto.order.response.OrderResponseDto;
import com.example.demo.dto.order.response.OrderShortResponseDto;
import com.example.demo.dto.order_item.request.OrderItemRequest;
import com.example.demo.dto.order_item.response.OrderItemResponseDto;
import com.example.demo.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponseDto> create(@RequestBody OrderCreateRequest request) {
        OrderResponseDto created = orderService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public ResponseEntity<List<OrderShortResponseDto>> getAll() {
        return ResponseEntity.ok(orderService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDto> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(orderService.getById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<OrderShortResponseDto>> search(@RequestParam String query) {
        return ResponseEntity.ok(orderService.search(query));
    }

    @GetMapping("/client/{client}")
    public ResponseEntity<List<OrderShortResponseDto>> getByClient(@PathVariable String client) {
        return ResponseEntity.ok(orderService.getByClient(client));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<OrderShortResponseDto>> getByName(@PathVariable String name) {
        return ResponseEntity.ok(orderService.getByName(name));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderResponseDto> update(
            @PathVariable UUID id,
            @RequestBody OrderUpdateRequest request
    ) {
        return ResponseEntity.ok(orderService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        orderService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> exists(@PathVariable UUID id) {
        return ResponseEntity.ok(orderService.exists(id));
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return ResponseEntity.ok(orderService.count());
    }

    @GetMapping("/{id}/items")
    public ResponseEntity<List<OrderItemResponseDto>> getItems(@PathVariable UUID id) {
        return ResponseEntity.ok(orderService.getItems(id));
    }

    @GetMapping("/{id}/items/{itemId}")
    public ResponseEntity<OrderItemResponseDto> getItem(
            @PathVariable UUID id,
            @PathVariable UUID itemId
    ) {
        return ResponseEntity.ok(orderService.getItem(id, itemId));
    }

    @PostMapping("/{id}/items")
    public ResponseEntity<OrderResponseDto> addItem(
            @PathVariable UUID id,
            @RequestBody OrderItemRequest request
    ) {
        OrderResponseDto updated = orderService.addItem(id, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(updated);
    }

    @PutMapping("/{id}/items/{itemId}")
    public ResponseEntity<OrderResponseDto> updateItem(
            @PathVariable UUID id,
            @PathVariable UUID itemId,
            @RequestBody OrderItemRequest request
    ) {
        return ResponseEntity.ok(orderService.updateItem(id, itemId, request));
    }

    @DeleteMapping("/{id}/items/{itemId}")
    public ResponseEntity<OrderResponseDto> removeItem(
            @PathVariable UUID id,
            @PathVariable UUID itemId
    ) {
        return ResponseEntity.ok(orderService.removeItem(id, itemId));
    }
}