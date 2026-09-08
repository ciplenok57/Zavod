package com.example.demo.service.impl;

import com.example.demo.dto.operation.request.OperationRequestDto;
import com.example.demo.dto.operation.request.OperationUpdateRequestDto;
import com.example.demo.dto.operation.response.OperationResponseDto;
import com.example.demo.dto.operation.response.OperationShortResponseDto;
import com.example.demo.entity.Operation;
import com.example.demo.entity.OperationCategory;
import com.example.demo.entity.User;
import com.example.demo.mapper.OperationMapper;
import com.example.demo.repository.OperationsRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.OperationCategoryService;
import com.example.demo.service.OperationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OperationServiceImpl implements OperationService {

    private final OperationsRepository operationRepository;
    private final OperationCategoryService categoryService;
    private final UserRepository userRepository;
    private final OperationMapper operationMapper;

    @Override
    public OperationResponseDto createOperation(OperationRequestDto requestDto) {
        Operation operation = operationMapper.toEntity(requestDto);
        return operationMapper.toResponseDto(operationRepository.save(operation));
    }

    @Override
    public List<OperationShortResponseDto> getAllOperations(UUID categoryId) {
        List<Operation> operations;
        if (categoryId == null) {
            operations = operationRepository.findAll();
        } else {
            operations = operationRepository.findAllByCategoryId(categoryId);
        }
        return operations.stream()
                .map(operationMapper::toOperationShortResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public OperationResponseDto getOperationById(UUID id) {
        Operation operation = operationRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Operation not found with id: " + id)
                );

        return operationMapper.toResponseDto(operation);
    }

    @Transactional
    @Override
    public OperationResponseDto updateOperation(UUID id, OperationUpdateRequestDto requestDto) {
        Operation operation = operationRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Operation not found with id: " + id)
                );

        operationMapper.updateEntity(operation, requestDto);

        return operationMapper.toResponseDto(operationRepository.save(operation));
    }

    @Override
    public void deleteOperation(UUID id) {
        Operation operation = operationRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Operation not found with id: " + id)
                );

        operationRepository.delete(operation);
    }

    @Override
    @Transactional
    public OperationResponseDto addUserToOperation(UUID operationId, UUID userId) {
        Operation operation = operationRepository.findById(operationId)
                .orElseThrow(() ->
                        new RuntimeException("Operation not found with id: " + operationId)
                );

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found with id: " + userId)
                );

        // Добавляем связь с обеих сторон
        operation.getResponsibleSet().add(user);
        user.getOperationsSet().add(operation);

        // Сохраняем только операцию (каскадное сохранение через mappedBy)
        Operation savedOperation = operationRepository.save(operation);

        // Возвращаем DTO без циклических ссылок
        return operationMapper.toResponseDto(savedOperation);
    }

    @Transactional
    @Override
    public OperationResponseDto removeUserFromOperation(UUID operationId, UUID userId) {
        Operation operation = operationRepository.findById(operationId)
                .orElseThrow(() -> new RuntimeException("Operation not found with id: " + operationId)
                );

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found with id: " + userId)
                );

        // Удаляем связь с обеих сторон
        operation.getResponsibleSet().remove(user);
        user.getOperationsSet().remove(operation);

        return operationMapper.toResponseDto(operationRepository.save(operation));
    }
//
//    @Override
//    public List<OperationResponseDto> getOperationsByCategory(UUID categoryId) {
//        OperationCategory category = categoryService.getCategoryEntityById(categoryId);
//
//        return operationRepository.findByCategory(category).stream()
//                .map(operationMapper::toResponseDto)
//                .collect(Collectors.toList());
//    }

}
