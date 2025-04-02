package com.asmadiyatech.onboarding.service;

import java.util.List;

import com.asmadiyatech.onboarding.entities.Project;

public interface ProjectService {

    Project createProject(String name, String description) ;

    Project assignUserToProject(Long projectId, Long userId) ;

    List<Project> getAllProjects() ;
}
