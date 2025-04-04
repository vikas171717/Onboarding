<<<<<<< HEAD
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
=======
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
>>>>>>> 8a8786ed1032cfcfec9908411367fd4fcb8deeb4
