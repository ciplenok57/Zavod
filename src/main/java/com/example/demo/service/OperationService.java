package com.example.demo.service;

import com.example.demo.dto.operation.request.OperationRequestDto;
import com.example.demo.dto.operation.request.OperationUpdateRequestDto;
import com.example.demo.dto.operation.response.OperationResponseDto;
import com.example.demo.dto.operation.response.OperationShortResponseDto;

import java.util.List;
import java.util.UUID;

public interface OperationService {

    OperationResponseDto createOperation(OperationRequestDto requestDto);

    List<OperationShortResponseDto> getAllOperations(UUID categoryId);

    OperationResponseDto getOperationById(UUID id);

    OperationResponseDto updateOperation(UUID id, OperationUpdateRequestDto requestDto);

    void deleteOperation(UUID id);

    OperationResponseDto addUserToOperation(UUID operationId, UUID userId);

    OperationResponseDto removeUserFromOperation(UUID operationId, UUID userId);

//    List<OperationResponseDto> getOperationsByCategory(UUID categoryId);

}
