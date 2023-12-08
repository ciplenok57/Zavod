package com.example.demo.entity;

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

    public Operation(UUID id, String name) {
        this.id = id;
        this.name = name;
    }
}
