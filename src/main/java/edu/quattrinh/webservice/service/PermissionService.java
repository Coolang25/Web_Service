package edu.quattrinh.webservice.service;

import edu.quattrinh.webservice.dto.request.PermissionRequest;
import edu.quattrinh.webservice.dto.response.PermissionResponse;
import edu.quattrinh.webservice.entity.Permission;
import edu.quattrinh.webservice.mapper.PermissionMapper;
import edu.quattrinh.webservice.repository.PermissionRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionService {
    PermissionRepository permissionRepository;
    PermissionMapper permissionMapper;
    public PermissionResponse create(PermissionRequest request) {
        Permission permission = permissionMapper.toPermission(request);

        permission = permissionRepository.save(permission);
        return permissionMapper.toPermissionResponse(permission);
    }

    public List<PermissionResponse> getAll() {
        var permission = permissionRepository.findAll();
        return permission.stream().map(permissionMapper::toPermissionResponse).collect(Collectors.toList());
    }

    public void delete(String permission) {
        permissionRepository.deleteById(permission);
    }
}
