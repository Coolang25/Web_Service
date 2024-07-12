package edu.quattrinh.webservice.mapper;

import edu.quattrinh.webservice.dto.request.PermissionRequest;
import edu.quattrinh.webservice.dto.request.UserCreationRequest;
import edu.quattrinh.webservice.dto.request.UserUpdateRequest;
import edu.quattrinh.webservice.dto.response.PermissionResponse;
import edu.quattrinh.webservice.dto.response.UserResponse;
import edu.quattrinh.webservice.entity.Permission;
import edu.quattrinh.webservice.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);
    PermissionResponse toPermissionResponse(Permission permission);
    void updatePermission(@MappingTarget Permission permission, PermissionRequest request);
}
