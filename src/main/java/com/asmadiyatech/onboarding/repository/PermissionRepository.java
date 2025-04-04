package com.asmadiyatech.onboarding.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.asmadiyatech.onboarding.entities.Permissions;

@Repository
public interface PermissionRepository extends JpaRepository<Permissions, Long> {

    Optional<Permissions> findByName(String name);

    boolean existsByName(String name);
}
