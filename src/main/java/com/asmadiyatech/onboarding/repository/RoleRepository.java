<<<<<<< HEAD
package com.asmadiyatech.onboarding.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.asmadiyatech.onboarding.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String name);

    boolean existsByName(String name);
}
=======
package com.asmadiyatech.onboarding.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.asmadiyatech.onboarding.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String name);

    boolean existsByName(String name);
}
>>>>>>> 8a8786ed1032cfcfec9908411367fd4fcb8deeb4
