package com.example.demo.service;

import com.example.demo.dto.templates.request.TemplateCreateRequestDto;
import com.example.demo.dto.templates.request.TemplateUpdateRequestDto;
import com.example.demo.dto.templates.response.TemplateResponseDto;
import com.example.demo.entity.Template;
import com.example.demo.entity.jsonb.AdditionalField;
import com.example.demo.entity.jsonb.TemplateOperation;

import java.util.List;
import java.util.UUID;

public interface TemplateService {

    TemplateResponseDto createTemplate(TemplateCreateRequestDto request);

    TemplateResponseDto getTemplateById(UUID id);

    List<TemplateResponseDto> getAllTemplates();

    List<TemplateResponseDto> getTemplatesByCategory(String category);

    List<TemplateResponseDto> searchTemplates(String query);

    TemplateResponseDto getTemplateByName(String name);

    TemplateResponseDto updateTemplate(UUID id, TemplateUpdateRequestDto request);

    TemplateResponseDto addAdditionalField(UUID templateId, AdditionalField field);

    TemplateResponseDto removeAdditionalField(UUID templateId, UUID fieldId);

    TemplateResponseDto addOperation(UUID templateId, TemplateOperation operation);

    TemplateResponseDto removeOperation(UUID templateId, UUID operationId);

    void deleteTemplate(UUID id);

}
