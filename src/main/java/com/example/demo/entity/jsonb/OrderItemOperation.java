package com.example.demo.entity.jsonb;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record OrderItemOperation(
        @JsonProperty("id") UUID id,
        @JsonProperty("name") String name,
        @JsonProperty("duration") Integer duration,
        @JsonProperty("status") String status,
        @JsonProperty("assignedTo") UUID assignedTo,
        @JsonProperty("startedAt") LocalDateTime startedAt,
        @JsonProperty("completedAt") LocalDateTime completedAt,
        @JsonProperty("prevOperations") List<UUID> prevOperations
) {
    // Компактный конструктор для значений по умолчанию
    public OrderItemOperation {
        if (prevOperations == null) {
            prevOperations = List.of();
        }
    }
}