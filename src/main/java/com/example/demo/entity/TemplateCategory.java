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
@Table(name = "template_category")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TemplateCategory {

    @Id
    @UuidGenerator
    private UUID id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Template> templates = new HashSet<>();

    public void addTemplate(Template template) {
        templates.add(template);
        template.setCategory(this);
    }

    public void removeTemplate(Template template) {
        templates.remove(template);
        template.setCategory(null);
    }

}
