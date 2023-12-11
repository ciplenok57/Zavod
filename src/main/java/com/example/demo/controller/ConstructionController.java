package com.example.demo.controller;

import com.example.demo.dto.construction.CreateConstructionDto;
import com.example.demo.entity.Construction;
import com.example.demo.entity.RealOperation;
import com.example.demo.service.ConstructionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/constructions")
@AllArgsConstructor
@CrossOrigin(originPatterns = "*")
public class ConstructionController {

    private final ConstructionService constructionService;

    @PostMapping
    public ResponseEntity<Construction> createConstruction(@RequestBody CreateConstructionDto createConstructionDto) {
        return new ResponseEntity<>(constructionService.createConstruction(createConstructionDto), HttpStatus.OK);
    }

    @GetMapping("/{constructionId}")
    public ResponseEntity<Construction> getOneConstruction(@PathVariable UUID constructionId) {
        return new ResponseEntity<>(constructionService.findOneConstruction(constructionId), HttpStatus.OK);
    }

    @GetMapping("/{constructionId}/graph")
    public ResponseEntity<Construction> getOneGraphConstruction(@PathVariable UUID constructionId) {
        return new ResponseEntity<>(constructionService.findOneConstruction(constructionId), HttpStatus.OK);
    }

    @GetMapping("/{constructionId}/operations/{operationId}")
    public ResponseEntity<RealOperation> getOperationInfo(@PathVariable UUID constructionId, @PathVariable UUID operationId) {
        return new ResponseEntity<>(constructionService.getRealOperation(constructionId, operationId), HttpStatus.OK);
    }

    @PutMapping("/{constructionId}/operations/{operationId}/start")
    public ResponseEntity<Construction> startOperation(@PathVariable UUID constructionId, @PathVariable UUID operationId) {
        return new ResponseEntity<>(constructionService.startOperation(constructionId, operationId), HttpStatus.OK);
    }

    @PutMapping("/{constructionId}/operations/{operationId}/stop")
    public ResponseEntity<Construction> stopOperation(@PathVariable UUID constructionId, @PathVariable UUID operationId) {
        return new ResponseEntity<>(constructionService.stopOperation(constructionId, operationId), HttpStatus.OK);
    }

}
