package com.example.demo.service;

import com.example.demo.dto.user.request.*;
import com.example.demo.dto.user.response.*;

import java.util.List;
import java.util.UUID;

public interface UserService {
    UserResponseDto createUser(UserCreateRequest request);

    List<UserResponseDto> getAllUsers();

    UserResponseDto getUserById(UUID id);

    UserResponseDto updateUser(UUID id, UserUpdateRequest request);

    void deleteUser(UUID id);
}