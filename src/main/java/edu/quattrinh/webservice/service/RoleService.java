package edu.quattrinh.webservice.service;

import edu.quattrinh.webservice.dto.request.PermissionRequest;
import edu.quattrinh.webservice.dto.request.RoleRequest;
import edu.quattrinh.webservice.dto.response.PermissionResponse;
import edu.quattrinh.webservice.dto.response.RoleResponse;
import edu.quattrinh.webservice.entity.Permission;
import edu.quattrinh.webservice.mapper.PermissionMapper;
import edu.quattrinh.webservice.mapper.RoleMapper;
import edu.quattrinh.webservice.repository.PermissionRepository;
import edu.quattrinh.webservice.repository.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleService {
    RoleRepository roleRepository;
    PermissionRepository permissionRepository;
    RoleMapper roleMapper;
    public RoleResponse create(RoleRequest request) {
        var role = roleMapper.toRole(request);

        var permissions = permissionRepository.findAllById(request.getPermission());

        role.setPermissions(new HashSet<>(permissions));

        role = roleRepository.save(role);

        return roleMapper.toRoleResponse(role);
    }

    public List<RoleResponse> getAll() {
        var roles = roleRepository.findAll();
        return roles.stream().map(roleMapper::toRoleResponse).collect(Collectors.toList());
    }

    public void delete(String role) {
        roleRepository.deleteById(role);
    }
}
