package com.example.demo.service;

import com.example.demo.dto.operation.CreateOperationDto;
import com.example.demo.entity.Operation;
import com.example.demo.repository.OperationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class OperationService {

    private final OperationRepository operationRepository;

    public Operation createNewOperation(CreateOperationDto createOperationDto){
        return operationRepository.save(new Operation(UUID.randomUUID(), createOperationDto.getName()));
    }

}
