package com.asmadiyatech.onboarding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.asmadiyatech.onboarding.entities.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long>{

}
