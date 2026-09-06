package com.example.demo.dto.operation.response;


import java.util.Set;
import java.util.UUID;

public record OperationShortResponseDto(
        UUID id,
        String name
) {
}