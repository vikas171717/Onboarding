<<<<<<< HEAD
package com.asmadiyatech.onboarding.service;

import java.util.List;

import com.asmadiyatech.onboarding.entities.Task;

public interface TaskService {

    Task createTask(String name, String description, Long projectId) ;

    Task assignUserToTask(Long taskId, Long userId) ;

    List<Task> getTasksByUser(Long userId) ;

    String updateTaskStatus(Long taskId) ;
}
=======
package com.asmadiyatech.onboarding.service;

import java.util.List;

import com.asmadiyatech.onboarding.entities.Task;

public interface TaskService {

    Task createTask(String name, String description, Long projectId) ;

    Task assignUserToTask(Long taskId, Long userId) ;

    List<Task> getTasksByUser(Long userId) ;

    String updateTaskStatus(Long taskId) ;
}
>>>>>>> 8a8786ed1032cfcfec9908411367fd4fcb8deeb4
