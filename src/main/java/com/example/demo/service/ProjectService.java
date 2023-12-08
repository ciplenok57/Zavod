package com.example.demo.service;

import com.example.demo.dto.project.CreateProjectDto;
import com.example.demo.entity.Construction;
import com.example.demo.entity.ProductTemplate;
import com.example.demo.entity.Project;
import com.example.demo.exceprion.DataNotFoundException;
import com.example.demo.repository.ConstructionRepository;
import com.example.demo.repository.ProductTemplateRepository;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.utils.OperationConverter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ProductTemplateRepository productTemplateRepository;
    private final ConstructionRepository constructionRepository;

    public List<Project> findAllProjects() {
        return projectRepository.findAll();
    }

    public Project findOneProject(UUID uuid) {
        return projectRepository.findById(uuid).orElseThrow(() -> new DataNotFoundException("Проект не найден"));
    }

    public Project createProject(CreateProjectDto createProjectDto) {

        List<Construction> constructionList = new ArrayList<>();

        for (UUID uuid : createProjectDto.getProjectTemplatesList()) {
            ProductTemplate productTemplate = productTemplateRepository.findById(uuid)
                    .orElseThrow(() -> new DataNotFoundException("Шаблон не найден"));
            Construction save = constructionRepository.save(OperationConverter.productTemplateToConstructionConverter(productTemplate));
            constructionList.add(save);
        }

        Project project = new Project();
        project.setId(UUID.randomUUID());
        project.setConstructions(constructionList);

        return projectRepository.save(project);
    }

}
