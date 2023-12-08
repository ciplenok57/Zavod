package com.example.demo.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Document("Project")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class Project {

    private UUID id;

    @DocumentReference
    private List<Construction> constructions = new ArrayList<>();

}
