package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "operation_category")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperationCategory {

    @Id
    @UuidGenerator
    private UUID id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Operation> operations = new HashSet<>();

    public void addOperation(Operation operation) {
        operations.add(operation);
        operation.setCategory(this);
    }

    public void removeOperation(Operation operation) {
        operations.remove(operation);
        operation.setCategory(null);
    }

}
