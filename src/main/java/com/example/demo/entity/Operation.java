package com.example.demo.entity;

import com.example.demo.dto.OperationPosition;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document("Operation")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class Operation {

    @Id
    private UUID id;

    private UUID internalId;

    private String name;

    private OperationPosition position;

    public Operation(UUID id, String name) {
        this.id = id;
        this.name = name;
    }
}
