package com.example.demo.dto.operation.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record OperationRequestDto(
        @NotBlank(message = "Название не может быть пустым")
        @Size(max = 255, message = "Название не должно превышать 255 символов")
        String name,

        @Size(max = 65535, message = "Содержимое слишком большое")
        String content,

        UUID categoryId
) {
}