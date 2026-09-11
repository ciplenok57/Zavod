package com.example.demo.entity.jsonb;

import com.fasterxml.jackson.annotation.JsonProperty;

public record OrderItemFieldValue(
        @JsonProperty("fieldId") String fieldId,
        @JsonProperty("fieldName") String fieldName,
        @JsonProperty("value") Object value
) {}