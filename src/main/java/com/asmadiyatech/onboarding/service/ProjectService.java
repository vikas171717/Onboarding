<<<<<<< HEAD
package com.asmadiyatech.onboarding.service;

import java.util.List;

import com.asmadiyatech.onboarding.entities.Project;

public interface ProjectService {

    Project createProject(String name, String description) ;

    Project assignUserToProject(Long projectId, Long userId) ;

    List<Project> getAllProjects() ;
}
=======
package com.asmadiyatech.onboarding.service;

import java.util.List;

import com.asmadiyatech.onboarding.entities.Project;

public interface ProjectService {

    Project createProject(String name, String description) ;

    Project assignUserToProject(Long projectId, Long userId) ;

    List<Project> getAllProjects() ;
}
>>>>>>> 8a8786ed1032cfcfec9908411367fd4fcb8deeb4
