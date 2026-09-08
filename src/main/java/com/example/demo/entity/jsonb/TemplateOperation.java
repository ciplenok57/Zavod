package com.example.demo.entity.jsonb;

import java.util.List;
import java.util.UUID;

public record TemplateOperation(
        UUID id,
        String name,
        List<UUID> prevOperations
) {
}
