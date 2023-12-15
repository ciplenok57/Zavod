
package com.example.demo.controller;

import com.example.demo.dto.PaginationRequestDto;
import com.example.demo.dto.productTemplate.CreateProductTemplateDto;
import com.example.demo.dto.productTemplate.OperationMockDto;
import com.example.demo.entity.Edge;
import com.example.demo.entity.Operation;
import com.example.demo.entity.ProductTemplate;
import com.example.demo.exceprion.DataNotFoundException;
import com.example.demo.repository.OperationRepository;
import com.example.demo.repository.ProductTemplateRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    public ResponseEntity<Page<ProductTemplate>> findAllTemplates(PaginationRequestDto paginationRequestDto) {
        return new ResponseEntity<>(productTemplateRepository.findAll(PageRequest.of(paginationRequestDto.getPage(),
                paginationRequestDto.getCount())), HttpStatus.OK);
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
        Set<Edge<Operation>> edges = new HashSet<>();

        for (OperationMockDto operationMockDto : createProductTemplateDto.getOperations()) {
            Operation operation = operationRepository.findById(operationMockDto.getOperationId())
                    .orElseThrow(() -> new DataNotFoundException("Операция не найдена"));
            operation.setId(operationMockDto.getGraphOperationId());
            operation.setInternalId(operationMockDto.getOperationId());
            operation.setPosition(operationMockDto.getPosition());
            operationsSet.add(operation);
        }

        for (Edge<UUID> edgeDto : createProductTemplateDto.getEdges()) {
            Edge<Operation> edge = new Edge<>();

            edge.setSource(operationsSet.stream().filter(el -> el.getId().equals(edgeDto.getSource()))
                    .findFirst().orElseThrow());

            edge.setTarget(operationsSet.stream().filter(el -> el.getId().equals(edgeDto.getTarget()))
                    .findFirst().orElseThrow());

            edges.add(edge);
        }

        productTemplate.setOperations(operationsSet);
        productTemplate.setEdgesList(edges);

        ProductTemplate save = productTemplateRepository.save(productTemplate);

        return new ResponseEntity<>(save, HttpStatus.OK);
    }

}
