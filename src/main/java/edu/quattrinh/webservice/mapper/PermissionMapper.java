package edu.quattrinh.webservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import edu.quattrinh.webservice.dto.request.PermissionRequest;
import edu.quattrinh.webservice.dto.response.PermissionResponse;
import edu.quattrinh.webservice.entity.Permission;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);

    PermissionResponse toPermissionResponse(Permission permission);

    void updatePermission(@MappingTarget Permission permission, PermissionRequest request);
}
