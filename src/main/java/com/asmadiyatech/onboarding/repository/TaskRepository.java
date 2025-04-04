package com.asmadiyatech.onboarding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.asmadiyatech.onboarding.entities.Task;

@Repository
public interface TaskRepository  extends JpaRepository<Task, Long>{

}
