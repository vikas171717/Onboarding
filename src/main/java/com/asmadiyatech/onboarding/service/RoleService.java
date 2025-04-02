package com.asmadiyatech.onboarding.service;

import java.util.List;
import java.util.Optional;

import com.asmadiyatech.onboarding.dto.RoleRequest;
import com.asmadiyatech.onboarding.entities.Role;

public interface RoleService {

    List<Role> getAllRoles() ;

    Optional<Role> getRoleByName(String name) ;

    Role createRole(RoleRequest roleRequest) ;

    Role removePermissionFromRole(Long roleId, Long permissionId) ;

    Role assignPermissionToRole(Long roleId, Long permissionId) ;
}
