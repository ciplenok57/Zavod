package com.example.demo.controller;


import com.example.demo.dto.template_category.request.TemplateCategoryCreateRequestDto;
import com.example.demo.dto.template_category.request.TemplateCategoryUpdateRequestDto;
import com.example.demo.dto.template_category.response.TemplateCategoryResponseDto;
import com.example.demo.service.TemplateCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/template-categories")
@RequiredArgsConstructor
public class TemplateCategoryController {

    private final TemplateCategoryService categoryService;

    @PostMapping
    public ResponseEntity<TemplateCategoryResponseDto> createCategory(@RequestBody TemplateCategoryCreateRequestDto request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.createCategory(request));
    }

    @GetMapping
    public ResponseEntity<List<TemplateCategoryResponseDto>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TemplateCategoryResponseDto> getCategoryById(@PathVariable UUID id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<TemplateCategoryResponseDto>> searchCategories(@RequestParam String query) {
        return ResponseEntity.ok(categoryService.searchCategories(query));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<TemplateCategoryResponseDto> getCategoryByName(@PathVariable String name) {
        return ResponseEntity.ok(categoryService.getCategoryByName(name));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TemplateCategoryResponseDto> updateCategory(
            @PathVariable UUID id,
            @RequestBody TemplateCategoryUpdateRequestDto request) {
        return ResponseEntity.ok(categoryService.updateCategory(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable UUID id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}