package com.example.demo.dto.order.response;

import java.time.LocalDate;
import java.util.UUID;

public record OrderShortResponseDto(
        UUID id,
        String name,
        String client,
        LocalDate deadline,
        int itemsCount
) {}