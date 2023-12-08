package com.example.demo.dto.project;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class CreateProjectDto {

    private String name;
    private List<UUID> projectTemplatesList;

}
