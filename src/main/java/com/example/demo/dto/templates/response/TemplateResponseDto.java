package com.example.demo.dto.templates.response;

import com.example.demo.dto.template_category.response.TemplateCategoryResponseDto;
import com.example.demo.entity.jsonb.AdditionalField;
import com.example.demo.entity.jsonb.TemplateOperation;

import java.util.List;
import java.util.UUID;

public record TemplateResponseDto(
        UUID id,
        String name,
        TemplateCategoryResponseDto category,
        String description,
        List<AdditionalField> additionalFields,
        List<TemplateOperation> operations
) {}