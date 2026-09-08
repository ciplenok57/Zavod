package com.example.demo.controller;

import com.example.demo.dto.templates.request.TemplateCreateRequestDto;
import com.example.demo.dto.templates.request.TemplateUpdateRequestDto;
import com.example.demo.dto.templates.response.TemplateResponseDto;
import com.example.demo.entity.jsonb.AdditionalField;
import com.example.demo.entity.jsonb.TemplateOperation;
import com.example.demo.service.TemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/templates")
@RequiredArgsConstructor
public class TemplateController {

    private final TemplateService templateService;

    @PostMapping
    public ResponseEntity<TemplateResponseDto> createTemplate(@RequestBody TemplateCreateRequestDto request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(templateService.createTemplate(request));
    }

    @GetMapping
    public ResponseEntity<List<TemplateResponseDto>> getAllTemplates() {
        return ResponseEntity.ok(templateService.getAllTemplates());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TemplateResponseDto> getTemplateById(@PathVariable UUID id) {
        return ResponseEntity.ok(templateService.getTemplateById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<TemplateResponseDto>> searchTemplates(@RequestParam String query) {
        return ResponseEntity.ok(templateService.searchTemplates(query));
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<TemplateResponseDto>> getTemplatesByCategory(@PathVariable String category) {
        return ResponseEntity.ok(templateService.getTemplatesByCategory(category));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<TemplateResponseDto> getTemplateByName(@PathVariable String name) {
        return ResponseEntity.ok(templateService.getTemplateByName(name));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TemplateResponseDto> updateTemplate(
            @PathVariable UUID id,
            @RequestBody TemplateUpdateRequestDto request) {
        return ResponseEntity.ok(templateService.updateTemplate(id, request));
    }

    @PostMapping("/{id}/fields")
    public ResponseEntity<TemplateResponseDto> addAdditionalField(
            @PathVariable UUID id,
            @RequestBody AdditionalField field) {
        return ResponseEntity.ok(templateService.addAdditionalField(id, field));
    }

    @DeleteMapping("/{id}/fields/{fieldId}")
    public ResponseEntity<TemplateResponseDto> removeAdditionalField(
            @PathVariable UUID id,
            @PathVariable UUID fieldId) {
        return ResponseEntity.ok(templateService.removeAdditionalField(id, fieldId));
    }

    @PostMapping("/{id}/operations")
    public ResponseEntity<TemplateResponseDto> addOperation(
            @PathVariable UUID id,
            @RequestBody TemplateOperation operation) {
        return ResponseEntity.ok(templateService.addOperation(id, operation));
    }

    @DeleteMapping("/{id}/operations/{operationId}")
    public ResponseEntity<TemplateResponseDto> removeOperation(
            @PathVariable UUID id,
            @PathVariable UUID operationId) {
        return ResponseEntity.ok(templateService.removeOperation(id, operationId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTemplate(@PathVariable UUID id) {
        templateService.deleteTemplate(id);
        return ResponseEntity.noContent().build();
    }

}
