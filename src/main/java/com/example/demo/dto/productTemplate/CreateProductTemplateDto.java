package com.example.demo.dto.productTemplate;

import com.example.demo.entity.Edge;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


@Getter
@Setter
public class CreateProductTemplateDto {
    private String name;
    private String description;
    private Set<OperationMockDto> operations = new HashSet<>();
    private Set<Edge<UUID>> edges = new HashSet<>();
    private Set<UUID> startOperations = new HashSet<>();
    private UUID finishOperation;
}
