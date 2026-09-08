package com.example.demo.dto.template_category.response;

import java.util.UUID;

public record TemplateCategoryResponseDto(
        UUID id,
        String name
) {}