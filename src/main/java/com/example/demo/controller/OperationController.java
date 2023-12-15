package com.example.demo.controller;

import com.example.demo.dto.PaginationRequestDto;
import com.example.demo.dto.operation.CreateOperationDto;
import com.example.demo.entity.Operation;
import com.example.demo.repository.OperationRepository;
import com.example.demo.service.OperationService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/operations")
@AllArgsConstructor
@CrossOrigin(originPatterns = "*")
public class OperationController {

    private final OperationRepository operationRepository;
    private final OperationService operationService;

    @GetMapping
    public ResponseEntity<Page<Operation>> findAllOperationsPaginated(PaginationRequestDto paginationRequestDto) {
        return new ResponseEntity<>(operationRepository.findAll(PageRequest.of(paginationRequestDto.getPage(),
                paginationRequestDto.getCount())), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Operation>> findAllOperations() {
        return new ResponseEntity<>(operationRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Operation> createOperation(@RequestBody CreateOperationDto createOperationDto) {
        return new ResponseEntity<>(operationService.createNewOperation(createOperationDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Operation> deleteOperation(@PathVariable UUID id) {
        operationService.deleteOperation(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
