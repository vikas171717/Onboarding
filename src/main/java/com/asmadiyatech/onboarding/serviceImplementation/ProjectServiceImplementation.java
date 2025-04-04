<<<<<<< HEAD
package com.asmadiyatech.onboarding.serviceImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.asmadiyatech.onboarding.entities.Project;
import com.asmadiyatech.onboarding.entities.User;
import com.asmadiyatech.onboarding.repository.ProjectRepository;
import com.asmadiyatech.onboarding.repository.UserRepository;
import com.asmadiyatech.onboarding.service.ProjectService;

@Service
public class ProjectServiceImplementation implements ProjectService{

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Project createProject(String name, String description) {
        Project project = new Project();
        project.setName(name);
        project.setDescription(description);
        return projectRepository.save(project);
    }

    @Override
    public Project assignUserToProject(Long projectId, Long userId) {
        Project project = projectRepository.findById(projectId)
            .orElseThrow(() -> new RuntimeException("Project not found"));
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));
        
        project.getAssignedUsers().add(user);
        return projectRepository.save(project);
    }

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAll() ;
    }
}

=======
package com.asmadiyatech.onboarding.serviceImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.asmadiyatech.onboarding.entities.Project;
import com.asmadiyatech.onboarding.entities.User;
import com.asmadiyatech.onboarding.repository.ProjectRepository;
import com.asmadiyatech.onboarding.repository.UserRepository;
import com.asmadiyatech.onboarding.service.ProjectService;

@Service
public class ProjectServiceImplementation implements ProjectService{

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Project createProject(String name, String description) {
        Project project = new Project();
        project.setName(name);
        project.setDescription(description);
        return projectRepository.save(project);
    }

    @Override
    public Project assignUserToProject(Long projectId, Long userId) {
        Project project = projectRepository.findById(projectId)
            .orElseThrow(() -> new RuntimeException("Project not found"));
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));
        
        project.getAssignedUsers().add(user);
        return projectRepository.save(project);
    }

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAll() ;
    }
}

>>>>>>> 8a8786ed1032cfcfec9908411367fd4fcb8deeb4
