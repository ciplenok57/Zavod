package com.example.demo.entity;

import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document("Material")
@Getter
@Setter
@Builder
public class Material {

    @Id
    private UUID id;

    private String name;

}
