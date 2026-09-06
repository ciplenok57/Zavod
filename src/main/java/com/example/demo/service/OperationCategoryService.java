package com.example.demo.service;

import com.example.demo.dto.operation_category.request.OperationCategoryRequestDto;
import com.example.demo.dto.operation_category.response.OperationCategoryResponseDto;

import java.util.List;
import java.util.UUID;

public interface OperationCategoryService {

    OperationCategoryResponseDto createCategory(OperationCategoryRequestDto requestDto);

    List<OperationCategoryResponseDto> getAllCategories();

    OperationCategoryResponseDto getCategoryById(UUID id);

    OperationCategoryResponseDto updateCategory(UUID id, OperationCategoryRequestDto requestDto);

    void deleteCategory(UUID id);



}
