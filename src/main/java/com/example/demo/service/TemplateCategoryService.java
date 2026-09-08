package com.example.demo.service;


import com.example.demo.dto.template_category.request.TemplateCategoryCreateRequestDto;
import com.example.demo.dto.template_category.request.TemplateCategoryUpdateRequestDto;
import com.example.demo.dto.template_category.response.TemplateCategoryResponseDto;

import java.util.List;
import java.util.UUID;

public interface TemplateCategoryService {

    TemplateCategoryResponseDto createCategory(TemplateCategoryCreateRequestDto request);

    TemplateCategoryResponseDto getCategoryById(UUID id);

    TemplateCategoryResponseDto getCategoryByName(String name);

    List<TemplateCategoryResponseDto> getAllCategories();

    List<TemplateCategoryResponseDto> searchCategories(String query);

    TemplateCategoryResponseDto updateCategory(UUID id, TemplateCategoryUpdateRequestDto request);

    void deleteCategory(UUID id);
}