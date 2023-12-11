package com.example.demo.controller;

import com.example.demo.dto.project.CreateProjectDto;
import com.example.demo.entity.Project;
import com.example.demo.service.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/projects")
@AllArgsConstructor
@CrossOrigin(originPatterns = "*")
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping
    public ResponseEntity<List<Project>> getAllProjects() {
        return new ResponseEntity<>(projectService.findAllProjects(), HttpStatus.OK);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Project> getOneProject(@PathVariable UUID uuid) {
        return new ResponseEntity<>(projectService.findOneProject(uuid), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Project> createProject(@RequestBody CreateProjectDto createProjectDto) {
        return new ResponseEntity<>(projectService.createProject(createProjectDto), HttpStatus.OK);
    }

}
