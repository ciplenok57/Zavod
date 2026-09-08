package com.example.demo.entity.jsonb;

import com.example.demo.entity.types.AdditionalFieldType;

import java.util.UUID;

public record AdditionalField(
        UUID id,
        String name,
        AdditionalFieldType type,
        boolean required,
        Object defaultValue
) {
}
