package com.asmadiyatech.onboarding.serviceImplementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.asmadiyatech.onboarding.config.JWTProvider;
import com.asmadiyatech.onboarding.entities.Project;
import com.asmadiyatech.onboarding.entities.Task;
import com.asmadiyatech.onboarding.entities.User;
import com.asmadiyatech.onboarding.exceptions.GlobalExceptionHandler;
import com.asmadiyatech.onboarding.repository.ProjectRepository;
import com.asmadiyatech.onboarding.repository.TaskRepository;
import com.asmadiyatech.onboarding.repository.UserRepository;
import com.asmadiyatech.onboarding.service.TaskService;

@Service
public class TaskServiceImplementation implements TaskService{

    private final WebMvcConfigurer corsConfigurer;

    private final JWTProvider JWTProvider;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    TaskServiceImplementation(JWTProvider JWTProvider, WebMvcConfigurer corsConfigurer) {
        this.JWTProvider = JWTProvider;
        this.corsConfigurer = corsConfigurer;
    }

    @Override
    public Task createTask(String name, String description, Long projectId) {
        Project project = projectRepository.findById(projectId)
            .orElseThrow(() -> new RuntimeException("Project not found"));

        Task task = new Task();
        task.setName(name);
        task.setDescription(description);
        task.setProject(project);
        return taskRepository.save(task);
    }

    @Override
    public Task assignUserToTask(Long taskId, Long userId) {
        Task task = taskRepository.findById(taskId)
            .orElseThrow(() -> new RuntimeException("Task not found"));
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));

        task.setAssignedUser(user);
        return taskRepository.save(task);
    }

    @Override
    public List<Task> getTasksByUser(Long userId) {
        return taskRepository.findAll().stream()
            .filter(task -> task.getAssignedUser() != null && task.getAssignedUser().getId().equals(userId))
            .collect(Collectors.toList());
    }

    @Override
    public String updateTaskStatus(Long taskId) {
        Task task = taskRepository.findById(taskId)
        .orElseThrow(() -> new RuntimeException("Task not found with id: " + taskId));

        if(!task.getTaskCompleted()){
            task.setTaskCompleted(true);
            taskRepository.save(task) ;
            return "Task status updated to true successfully" ;
        }else{
            return "Task status already true" ;
        }
    }
}

