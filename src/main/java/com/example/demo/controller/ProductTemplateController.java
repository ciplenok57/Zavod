
package com.example.demo.controller;

import com.example.demo.dto.productTemplate.CreateProductTemplateDto;
import com.example.demo.dto.productTemplate.OperationMockDto;
import com.example.demo.entity.Edge;
import com.example.demo.entity.Operation;
import com.example.demo.entity.ProductTemplate;
import com.example.demo.exceprion.DataNotFoundException;
import com.example.demo.repository.OperationRepository;
import com.example.demo.repository.ProductTemplateRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/product-template")
@AllArgsConstructor
@CrossOrigin(originPatterns = "*")
public class ProductTemplateController {

    private final ProductTemplateRepository productTemplateRepository;
    private final OperationRepository operationRepository;

    @GetMapping
    public ResponseEntity<List<ProductTemplate>> findAllTemplates() {
        return new ResponseEntity<>(productTemplateRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{templateId}")
    public ResponseEntity<ProductTemplate> findTemplate(@PathVariable UUID templateId) {
        return new ResponseEntity<>(productTemplateRepository.findById(templateId)
                .orElseThrow(() -> new DataNotFoundException("Шаблон не найден")), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductTemplate> createTemplate(@RequestBody CreateProductTemplateDto createProductTemplateDto) {

        ProductTemplate productTemplate = new ProductTemplate();
        productTemplate.setId(UUID.randomUUID());
        productTemplate.setName(createProductTemplateDto.getName());
        productTemplate.setDescription(createProductTemplateDto.getDescription());

        Set<Operation> operationsSet = new HashSet<>();
        Set<Operation> startOperations = new HashSet<>();
        Set<Edge<Operation>> edges = new HashSet<>();

        for (OperationMockDto operationMockDto : createProductTemplateDto.getOperations()) {
            Operation operation = operationRepository.findById(operationMockDto.getOperationId())
                    .orElseThrow(() -> new DataNotFoundException("Операция не найдена"));
            operation.setInternalId(operationMockDto.getGraphOperationId());
            operationsSet.add(operation);
        }

        for (UUID operationId : createProductTemplateDto.getStartOperations()) {
            Operation operation =
                    operationsSet.stream().filter(el -> el.getInternalId().equals(operationId))
                            .findFirst().orElseThrow();
            startOperations.add(operation);
        }

        for (Edge<UUID> edgeDto : createProductTemplateDto.getEdges()) {
            Edge<Operation> edge = new Edge<>();

            edge.setSource(operationsSet.stream().filter(el -> el.getInternalId().equals(edgeDto.getSource()))
                    .findFirst().orElseThrow());

            edge.setTarget(operationsSet.stream().filter(el -> el.getInternalId().equals(edgeDto.getTarget()))
                    .findFirst().orElseThrow());

            edges.add(edge);
        }

        productTemplate.setOperations(operationsSet);
        productTemplate.setFinishOperation(operationsSet.stream().filter(el -> el.getInternalId()
                        .equals(createProductTemplateDto.getFinishOperation()))
                .findFirst().orElseThrow());
        productTemplate.setStartOperations(startOperations);
        productTemplate.setEdgesList(edges);

        ProductTemplate save = productTemplateRepository.save(productTemplate);

        return new ResponseEntity<>(save, HttpStatus.OK);
    }

}
