package com.example.demo.dto.operation.request;

import jakarta.validation.constraints.Size;

import java.util.UUID;

public record OperationUpdateRequestDto(
        @Size(max = 255, message = "Название не должно превышать 255 символов")
        String name,

        @Size(max = 65535, message = "Содержимое слишком большое")
        String content,

        UUID categoryId
) {
}