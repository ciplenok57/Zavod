package com.example.demo.service;

import com.example.demo.dto.templates.request.TemplateCreateRequestDto;
import com.example.demo.dto.templates.request.TemplateUpdateRequestDto;
import com.example.demo.dto.templates.response.TemplateResponseDto;
import com.example.demo.entity.Template;
import com.example.demo.entity.jsonb.AdditionalField;
import com.example.demo.entity.jsonb.TemplateOperation;
import com.example.demo.mapper.TemplateMapper;
import com.example.demo.repository.TemplateRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TemplateServiceImpl implements TemplateService {

    private final TemplateRepository templateRepository;
    private final TemplateMapper templateMapper;

    @Override
    @Transactional
    public TemplateResponseDto createTemplate(TemplateCreateRequestDto request) {
        Template template = templateMapper.toEntity(request);

        if (template.getAdditionalFields() == null) {
            template.setAdditionalFields(new ArrayList<>());
        }
        if (template.getOperations() == null) {
            template.setOperations(new ArrayList<>());
        }

        Template saved = templateRepository.save(template);
        return templateMapper.toResponseDto(saved);
    }

    @Override
    public TemplateResponseDto getTemplateById(UUID id) {
        Template template = templateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Template not found with id: " + id));
        return templateMapper.toResponseDto(template);
    }

    @Override
    public List<TemplateResponseDto> getAllTemplates() {
        return templateMapper.toResponseDtoList(templateRepository.findAll());
    }

    @Override
    public List<TemplateResponseDto> getTemplatesByCategory(String category) {
        return templateMapper.toResponseDtoList(templateRepository.findByCategory(category));
    }

    @Override
    public List<TemplateResponseDto> searchTemplates(String query) {
        return templateMapper.toResponseDtoList(templateRepository.searchTemplates(query));
    }

    @Override
    public TemplateResponseDto getTemplateByName(String name) {
        Template template = templateRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Template not found with name: " + name));
        return templateMapper.toResponseDto(template);
    }

    @Override
    @Transactional
    public TemplateResponseDto updateTemplate(UUID id, TemplateUpdateRequestDto request) {
        Template template = templateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Template not found with id: " + id));

        templateMapper.updateEntity(request, template);

        Template updated = templateRepository.save(template);
        return templateMapper.toResponseDto(updated);
    }

    @Override
    @Transactional
    public TemplateResponseDto addAdditionalField(UUID templateId, AdditionalField field) {
        Template template = templateRepository.findById(templateId)
                .orElseThrow(() -> new RuntimeException("Template not found with id: " + templateId));

        List<AdditionalField> fields = template.getAdditionalFields();
        if (fields == null) {
            fields = new ArrayList<>();
        }
        fields.add(field);
        template.setAdditionalFields(fields);

        Template updated = templateRepository.save(template);
        return templateMapper.toResponseDto(updated);
    }

    @Override
    @Transactional
    public TemplateResponseDto removeAdditionalField(UUID templateId, UUID fieldId) {
        Template template = templateRepository.findById(templateId)
                .orElseThrow(() -> new RuntimeException("Template not found with id: " + templateId));

        List<AdditionalField> fields = template.getAdditionalFields();
        if (fields != null) {
            fields.removeIf(f -> f.id().equals(fieldId));
            template.setAdditionalFields(fields);
        }

        Template updated = templateRepository.save(template);
        return templateMapper.toResponseDto(updated);
    }

    @Override
    @Transactional
    public TemplateResponseDto addOperation(UUID templateId, TemplateOperation operation) {
        Template template = templateRepository.findById(templateId)
                .orElseThrow(() -> new RuntimeException("Template not found with id: " + templateId));

        List<TemplateOperation> operations = template.getOperations();
        if (operations == null) {
            operations = new ArrayList<>();
        }
        operations.add(operation);
        template.setOperations(operations);

        Template updated = templateRepository.save(template);
        return templateMapper.toResponseDto(updated);
    }

    @Override
    @Transactional
    public TemplateResponseDto removeOperation(UUID templateId, UUID operationId) {
        Template template = templateRepository.findById(templateId)
                .orElseThrow(() -> new RuntimeException("Template not found with id: " + templateId));

        List<TemplateOperation> operations = template.getOperations();
        if (operations != null) {
            operations.removeIf(op -> op.id().equals(operationId));
            template.setOperations(operations);
        }

        Template updated = templateRepository.save(template);
        return templateMapper.toResponseDto(updated);
    }

    @Override
    @Transactional
    public void deleteTemplate(UUID id) {
        if (!templateRepository.existsById(id)) {
            throw new RuntimeException("Template not found with id: " + id);
        }
        templateRepository.deleteById(id);
    }
}
