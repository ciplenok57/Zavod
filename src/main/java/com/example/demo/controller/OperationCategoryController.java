package com.example.demo.controller;

import com.example.demo.dto.operation_category.request.OperationCategoryRequestDto;
import com.example.demo.dto.operation_category.response.OperationCategoryResponseDto;
import com.example.demo.entity.OperationCategory;
import com.example.demo.service.OperationCategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/operations-categories")
@RequiredArgsConstructor
public class OperationCategoryController {

    private final OperationCategoryService categoryService;

    @PostMapping
    public ResponseEntity<OperationCategoryResponseDto> createCategory(
            @Valid @RequestBody OperationCategoryRequestDto requestDto) {

        OperationCategoryResponseDto created = categoryService.createCategory(requestDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(created);
    }

    @GetMapping
    public ResponseEntity<List<OperationCategoryResponseDto>> getAllCategories() {
        List<OperationCategoryResponseDto> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OperationCategoryResponseDto> getCategoryById(
            @PathVariable UUID id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OperationCategoryResponseDto> updateCategory(
            @PathVariable UUID id,
            @Valid @RequestBody OperationCategoryRequestDto requestDto) {
        OperationCategoryResponseDto updated = categoryService.updateCategory(id, requestDto);

        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(
            @PathVariable UUID id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

}