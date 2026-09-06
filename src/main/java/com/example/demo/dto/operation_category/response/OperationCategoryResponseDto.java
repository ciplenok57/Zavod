package com.example.demo.dto.operation_category.response;

import java.util.UUID;

public record OperationCategoryResponseDto(
        UUID id,
        String name,
        Integer operationsCount
) {
}