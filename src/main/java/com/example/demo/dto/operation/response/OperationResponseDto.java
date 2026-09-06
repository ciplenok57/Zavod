package com.example.demo.dto.operation.response;

import com.example.demo.dto.operation_category.response.OperationCategoryResponseDto;
import com.example.demo.dto.user.response.UserResponseDto;

import java.util.Set;
import java.util.UUID;

public record OperationResponseDto(
        UUID id,
        String name,
        String content,
        OperationCategoryResponseDto category,
        Set<UserResponseDto> responsibleUsers
) {
}