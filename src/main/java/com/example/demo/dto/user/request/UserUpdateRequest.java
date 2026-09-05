package com.example.demo.dto.user.request;

import com.example.demo.entity.UserRoles;

public record UserUpdateRequest(
        String firstName,

        String lastName,

        String middleName,

        String phone,

        UserRoles role
) {
}