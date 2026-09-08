package com.example.demo.entity;

import com.example.demo.entity.jsonb.AdditionalField;
import com.example.demo.entity.jsonb.TemplateOperation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "templates")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Template {

    @Id
    @UuidGenerator
    private UUID id;

    private String name;

    @ManyToOne
    private TemplateCategory category;

    private String description;

    @Column(name = "additional_fields", columnDefinition = "JSONB")
    @JdbcTypeCode(SqlTypes.JSON)
    private List<AdditionalField> additionalFields = new ArrayList<>();

    @Column(name = "operations", columnDefinition = "JSONB")
    @JdbcTypeCode(SqlTypes.JSON)
    private List<TemplateOperation> operations = new ArrayList();

}
