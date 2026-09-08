package com.example.demo.service.impl;


import com.example.demo.dto.template_category.request.TemplateCategoryCreateRequestDto;
import com.example.demo.dto.template_category.request.TemplateCategoryUpdateRequestDto;
import com.example.demo.dto.template_category.response.TemplateCategoryResponseDto;
import com.example.demo.entity.TemplateCategory;
import com.example.demo.mapper.TemplateCategoryMapper;
import com.example.demo.repository.TemplateCategoryRepository;
import com.example.demo.service.TemplateCategoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class TemplateCategoryServiceImpl implements TemplateCategoryService {

    private final TemplateCategoryRepository categoryRepository;
    private final TemplateCategoryMapper categoryMapper;

    @Override
    @Transactional
    public TemplateCategoryResponseDto createCategory(TemplateCategoryCreateRequestDto request) {
        if (categoryRepository.existsByName(request.name())) {
            throw new RuntimeException("Category with name " + request.name() + " already exists");
        }

        TemplateCategory category = categoryMapper.toEntity(request);
        TemplateCategory saved = categoryRepository.save(category);
        return categoryMapper.toResponseDto(saved);
    }

    @Override
    public TemplateCategoryResponseDto getCategoryById(UUID id) {
        TemplateCategory category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
        return categoryMapper.toResponseDto(category);
    }

    @Override
    public TemplateCategoryResponseDto getCategoryByName(String name) {
        TemplateCategory category = categoryRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Category not found with name: " + name));
        return categoryMapper.toResponseDto(category);
    }

    @Override
    public List<TemplateCategoryResponseDto> getAllCategories() {
        return categoryMapper.toResponseDtoList(categoryRepository.findAll());
    }

    @Override
    public List<TemplateCategoryResponseDto> searchCategories(String query) {
        return categoryMapper.toResponseDtoList(categoryRepository.searchCategories(query));
    }

    @Override
    @Transactional
    public TemplateCategoryResponseDto updateCategory(UUID id, TemplateCategoryUpdateRequestDto request) {
        TemplateCategory category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));

        if (request.name() != null && !request.name().equals(category.getName())) {
            if (categoryRepository.existsByName(request.name())) {
                throw new RuntimeException("Category with name " + request.name() + " already exists");
            }
        }

        categoryMapper.updateEntity(request, category);
        TemplateCategory updated = categoryRepository.save(category);
        return categoryMapper.toResponseDto(updated);
    }

    @Override
    @Transactional
    public void deleteCategory(UUID id) {
        if (!categoryRepository.existsById(id)) {
            throw new RuntimeException("Category not found with id: " + id);
        }
        categoryRepository.deleteById(id);
    }
}