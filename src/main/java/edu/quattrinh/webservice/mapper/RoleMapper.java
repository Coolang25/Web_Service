package edu.quattrinh.webservice.mapper;

import edu.quattrinh.webservice.dto.request.PermissionRequest;
import edu.quattrinh.webservice.dto.request.RoleRequest;
import edu.quattrinh.webservice.dto.response.PermissionResponse;
import edu.quattrinh.webservice.dto.response.RoleResponse;
import edu.quattrinh.webservice.entity.Permission;
import edu.quattrinh.webservice.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);
    RoleResponse toRoleResponse(Role role);
}
