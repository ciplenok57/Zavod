package com.example.demo.dto.user.response;


import com.example.demo.entity.UserRoles;

import java.util.UUID;

public record UserResponseDto(
        UUID id,
        String username,
        String email,
        String firstName,
        String lastName,
        String middleName,
        String phone,
        UserRoles role
) {}