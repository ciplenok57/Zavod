package com.example.demo.service.impl;

import com.example.demo.dto.operation_category.request.OperationCategoryRequestDto;
import com.example.demo.dto.operation_category.response.OperationCategoryResponseDto;
import com.example.demo.entity.OperationCategory;
import com.example.demo.mapper.OperationCategoryMapper;
import com.example.demo.repository.OperationCategoryRepository;
import com.example.demo.service.OperationCategoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OperationCategoryServiceImpl implements OperationCategoryService {

    private final OperationCategoryRepository categoryRepository;

    private final OperationCategoryMapper categoryMapper;

    @Override
    public OperationCategoryResponseDto createCategory(OperationCategoryRequestDto requestDto) {
        OperationCategory category = categoryMapper.toEntity(requestDto);
        return categoryMapper.toResponseDto(categoryRepository.save(category));
    }

    @Transactional
    @Override
    public List<OperationCategoryResponseDto> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(categoryMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public OperationCategoryResponseDto getCategoryById(UUID id) {
        OperationCategory category = categoryRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Category not found with id: " + id)
                );

        return categoryMapper.toResponseDto(category);
    }

    @Override
    public OperationCategoryResponseDto updateCategory(UUID id, OperationCategoryRequestDto requestDto) {
        OperationCategory category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id)
                );

        categoryMapper.updateEntity(category, requestDto);
        return categoryMapper.toResponseDto(categoryRepository.save(category));
    }

    @Override
    public void deleteCategory(UUID id) {
        categoryRepository.deleteById(id);
    }
}
