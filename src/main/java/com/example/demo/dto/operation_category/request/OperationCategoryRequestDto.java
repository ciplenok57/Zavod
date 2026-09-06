package com.example.demo.dto.operation_category.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record OperationCategoryRequestDto(
        @NotBlank(message = "Название категории не может быть пустым")
        @Size(max = 100, message = "Название не должно превышать 100 символов")
        String name
) {
}