package com.asmadiyatech.onboarding.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asmadiyatech.onboarding.entities.Project;
import com.asmadiyatech.onboarding.service.ProjectService;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/onboarding/projects")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @PostMapping("/create")
    public ResponseEntity<Project> createProject(@RequestHeader("Authorization") String token, @RequestBody Project project) {
        return ResponseEntity.ok(projectService.createProject(project.getName(), project.getDescription()));
    }

    @PostMapping("/{projectId}/assign/{userId}")
    public ResponseEntity<Project> assignUserToProject(@RequestHeader("Authorization") String token, @PathVariable Long projectId, @PathVariable Long userId) {
        return ResponseEntity.ok(projectService.assignUserToProject(projectId, userId));
    }

    @GetMapping("/getAllProjects")
    public ResponseEntity<List<Project>> getAllProjects(@RequestHeader("Authorization") String token) {
        List<Project> projectList = projectService.getAllProjects() ;
        return ResponseEntity.ok(projectList) ;
    }
    
}

