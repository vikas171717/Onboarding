package com.asmadiyatech.onboarding.service;

import java.util.List;
import java.util.Optional;

import com.asmadiyatech.onboarding.dto.PermissionRequest;
import com.asmadiyatech.onboarding.entities.Permissions;

public interface PermissionService {

    List<Permissions> getAllPermissions() ;

    Optional<Permissions> findByName(String name) ;

    Permissions createPermission(PermissionRequest permissionRequest) ;

    Permissions updatePermission(Long id, Permissions permission) ;

    void deletePermission(Long id) ;

}
