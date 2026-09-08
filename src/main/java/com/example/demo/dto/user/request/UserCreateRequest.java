package com.example.demo.dto.user.request;

import com.example.demo.entity.types.UserRoles;

public record UserCreateRequest(
        String username,

        String email,

        String password,

        String firstName,

        String lastName,

        String middleName,

        String phone,

        UserRoles role
) {
}