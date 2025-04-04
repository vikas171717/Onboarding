package com.asmadiyatech.onboarding.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import com.asmadiyatech.onboarding.dto.RoleRequest;
import com.asmadiyatech.onboarding.entities.Role;
import com.asmadiyatech.onboarding.service.RoleService;

@RestController
@RequestMapping("/api/onboarding/role")
public class RoleController {

    @Autowired
    private RoleService roleService ;

    @GetMapping("/getAllRoles")
    public ResponseEntity<List<Role>> getAllRoles(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(roleService.getAllRoles());
    }

    @GetMapping("/{name}")
    public ResponseEntity<Role> getRoleByName(@RequestHeader("Authorization") String token, @PathVariable String name) {
        Optional<Role> role = roleService.getRoleByName(name);
        return role.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<Role> createRole(@RequestBody RoleRequest roleRequest) {
        return ResponseEntity.ok(roleService.createRole(roleRequest));
    }

    @PostMapping("/{roleId}/permissions/{permissionId}")
    public Role assignPermissionToRole(@RequestHeader("Authorization") String token, @PathVariable Long roleId, @PathVariable Long permissionId) {
        return roleService.assignPermissionToRole(roleId, permissionId);
    }

    @DeleteMapping("/{roleId}/permissions/{permissionId}")
    public Role removePermissionFromRole(@RequestHeader("Authorization") String token, @PathVariable Long roleId, @PathVariable Long permissionId) {
        return roleService.removePermissionFromRole(roleId, permissionId);
    }
}