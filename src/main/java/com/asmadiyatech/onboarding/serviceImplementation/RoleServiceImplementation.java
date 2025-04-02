package com.asmadiyatech.onboarding.serviceImplementation;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asmadiyatech.onboarding.dto.RoleRequest;
import com.asmadiyatech.onboarding.entities.Permissions;
import com.asmadiyatech.onboarding.entities.Role;
import com.asmadiyatech.onboarding.repository.RoleRepository;
import com.asmadiyatech.onboarding.service.RoleService;
import com.asmadiyatech.onboarding.repository.PermissionRepository;

@Service
public class RoleServiceImplementation implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Optional<Role> getRoleByName(String name) {
        return roleRepository.findByName(name);
    }

    @Override
    public Role createRole(RoleRequest roleRequest) {
        // Convert role name to uppercase (optional for consistency)
        String roleName = roleRequest.getRoleName().toUpperCase();

        // Create a new Role entity
        Role role = new Role();
        role.setName(roleName); // Now setting role name as String

        // Fetch permissions based on provided IDs and assign them
        Set<Permissions> permissions = new HashSet<>(permissionRepository.findAllById(roleRequest.getPermissionIds()));
        role.setPermissions(permissions);

        // Save the new role in the database
        return roleRepository.save(role);
    }

    @Override
    public Role assignPermissionToRole(Long roleId, Long permissionId) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Role not found"));
        Permissions permission = permissionRepository.findById(permissionId)
                .orElseThrow(() -> new RuntimeException("Permission not found"));
        
        role.getPermissions().add(permission);
        return roleRepository.save(role);
    }

    @Override
    public Role removePermissionFromRole(Long roleId, Long permissionId) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Role not found"));
        Permissions permission = permissionRepository.findById(permissionId)
                .orElseThrow(() -> new RuntimeException("Permission not found"));
        
        role.getPermissions().remove(permission);
        return roleRepository.save(role);
    }

}
