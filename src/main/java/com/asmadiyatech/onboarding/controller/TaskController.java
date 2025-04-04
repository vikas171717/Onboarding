package com.asmadiyatech.onboarding.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asmadiyatech.onboarding.entities.Task;
import com.asmadiyatech.onboarding.service.TaskService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/onboarding/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping("/create")
    public ResponseEntity<Task> createTask(@RequestHeader("Authorization") String token, @RequestBody Task task) {
        return ResponseEntity.ok(taskService.createTask(task.getName(), task.getDescription(), task.getProject().getId()));
    }

    @PostMapping("/{taskId}/assign/{userId}")
    public ResponseEntity<Task> assignUserToTask(@RequestHeader("Authorization") String token, @PathVariable Long taskId, @PathVariable Long userId) {
        return ResponseEntity.ok(taskService.assignUserToTask(taskId, userId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Task>> getTasksByUser(@RequestHeader("Authorization") String token, @PathVariable Long userId) {
        return ResponseEntity.ok(taskService.getTasksByUser(userId));
    }

    @PostMapping("/update-task-status/{taskId}")
    public ResponseEntity<String> updateTaskStatus(@RequestHeader("Authorization") String token, @PathVariable Long taskId) {
        String message = taskService.updateTaskStatus(taskId) ;
        return ResponseEntity.ok(message) ;
    }
    
}

