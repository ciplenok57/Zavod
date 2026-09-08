package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.*;

@Entity
@Table(name = "operations")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Operation {

    @Id
    @UuidGenerator
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @ManyToMany(mappedBy = "operationsSet")
    private Set<User> responsibleSet = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private OperationCategory category;

}
