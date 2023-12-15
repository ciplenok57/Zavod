package com.example.demo.entity;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Document("productTemplate")
@Getter
@Setter
public class ProductTemplate {

    @Id
    private UUID id;

    private String name;

    private String description;

    private Set<Operation> operations = new HashSet<>();

    private Set<Edge<Operation>> edgesList = new HashSet<>();

//    private Set<Operation> startOperations = new HashSet<>();
//
//    private Operation finishOperation;

}
