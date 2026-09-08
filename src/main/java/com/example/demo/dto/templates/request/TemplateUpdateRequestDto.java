package com.example.demo.dto.templates.request;

import com.example.demo.entity.jsonb.AdditionalField;
import com.example.demo.entity.jsonb.TemplateOperation;

import java.util.List;
import java.util.UUID;

public record TemplateUpdateRequestDto(
        String name,
        UUID categoryId,
        String description,
        List<AdditionalField> additionalFields,
        List<TemplateOperation> operations
) {}