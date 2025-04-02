package com.asmadiyatech.onboarding.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import com.asmadiyatech.onboarding.dto.PermissionRequest;
import com.asmadiyatech.onboarding.entities.Permissions;
import com.asmadiyatech.onboarding.service.PermissionService;

@RestController
@RequestMapping("/api/onboarding/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService ;

    @GetMapping("/getAllPermissions")
    public ResponseEntity<List<Permissions>> getAllPermissions(@RequestHeader("Authorization") String token) {
        List<Permissions> permissionsList = permissionService.getAllPermissions() ;
        return ResponseEntity.ok(permissionsList);
    }

    @GetMapping("/{name}")
    public ResponseEntity<Permissions> getPermissionByName(@RequestHeader("Authorization") String token, @PathVariable String name) {
        Optional<Permissions> permission = permissionService.findByName(name);
        return permission.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<Permissions> createPermission(@RequestBody PermissionRequest permissionRequest) {
        return ResponseEntity.ok(permissionService.createPermission(permissionRequest));
    }

    @PutMapping("/{id}")
    public Permissions updatePermission(@RequestHeader("Authorization") String token, @PathVariable Long id, @RequestBody Permissions permission) {
        return permissionService.updatePermission(id, permission);
    }

    @DeleteMapping("/{id}")
    public void deletePermission(@RequestHeader("Authorization") String token, @PathVariable Long id) {
        permissionService.deletePermission(id);
    }
}
