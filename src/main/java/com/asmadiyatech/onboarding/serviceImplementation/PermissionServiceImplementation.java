package com.asmadiyatech.onboarding.serviceImplementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asmadiyatech.onboarding.dto.PermissionRequest;
import com.asmadiyatech.onboarding.entities.Permissions;
import com.asmadiyatech.onboarding.repository.PermissionRepository;
import com.asmadiyatech.onboarding.service.PermissionService;

@Service
public class PermissionServiceImplementation implements PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public List<Permissions> getAllPermissions() {
        return permissionRepository.findAll();
    }

    @Override
    public Optional<Permissions> findByName(String name) {
        return permissionRepository.findByName(name);
    }

    @Override
    public Permissions createPermission(PermissionRequest permissionRequest) {
        if (permissionRepository.findByName(permissionRequest.getName()).isPresent()) {
            throw new RuntimeException("Permission already exists");
        }

        Permissions permission = new Permissions();
        permission.setName(permissionRequest.getName());
        return permissionRepository.save(permission);
    }

    @Override
    public Permissions updatePermission(Long id, Permissions permission) {
        Permissions existing = permissionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Permission not found"));
        existing.setName(permission.getName());
        return permissionRepository.save(existing);
    }

    @Override
    public void deletePermission(Long id) {
        permissionRepository.deleteById(id);
    }
}
