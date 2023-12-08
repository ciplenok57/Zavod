package com.example.demo.entity;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Document("Construction")
@Getter
@Setter
public class Construction {

    @Id
    private UUID id;

    private String name;

    private Set<RealOperation> operations = new HashSet<>();

    private Set<Edge<RealOperation>> edgesList = new HashSet<>();

    private Set<RealOperation> startOperations = new HashSet<>();

    private RealOperation finishOperation;

    public void addNewOperation(RealOperation operation){
        operations.add(operation);
    }


}
