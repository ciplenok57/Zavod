package com.example.demo.controller;

import com.example.demo.dto.operation.request.OperationRequestDto;
import com.example.demo.dto.operation.request.OperationUpdateRequestDto;
import com.example.demo.dto.operation.response.OperationResponseDto;
import com.example.demo.dto.operation.response.OperationShortResponseDto;
import com.example.demo.service.OperationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/operations")
@RequiredArgsConstructor
public class OperationController {

    private final OperationService operationService;

    @PostMapping
    public ResponseEntity<OperationResponseDto> createOperation(
            @Valid @RequestBody OperationRequestDto requestDto) {
        OperationResponseDto created = operationService.createOperation(requestDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(created);
    }

    @GetMapping
    public ResponseEntity<List<OperationShortResponseDto>> getAllOperations(
            @RequestParam(required = false) UUID categoryId) {
        return ResponseEntity.ok(operationService.getAllOperations(categoryId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OperationResponseDto> getOperationById(@PathVariable UUID id) {
        return ResponseEntity.ok(operationService.getOperationById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OperationResponseDto> updateOperation(
            @PathVariable UUID id,
            @Valid @RequestBody OperationUpdateRequestDto requestDto) {
        return ResponseEntity.ok(operationService.updateOperation(id, requestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOperation(@PathVariable UUID id) {
        operationService.deleteOperation(id);
        return ResponseEntity.ok(null);
    }

    @PostMapping("/{operationId}/users/{userId}")
    public ResponseEntity<OperationResponseDto> addUserToOperation(
            @PathVariable UUID operationId,
            @PathVariable UUID userId) {
        return ResponseEntity.ok(operationService.addUserToOperation(operationId, userId));
    }

    @DeleteMapping("/{operationId}/users/{userId}")
    public ResponseEntity<OperationResponseDto> removeUserFromOperation(
            @PathVariable UUID operationId, @PathVariable UUID userId) {
        return ResponseEntity.ok(operationService.removeUserFromOperation(operationId, userId));
    }

}
