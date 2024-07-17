package edu.quattrinh.webservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import edu.quattrinh.webservice.dto.request.RoleRequest;
import edu.quattrinh.webservice.dto.response.RoleResponse;
import edu.quattrinh.webservice.entity.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);

    RoleResponse toRoleResponse(Role role);
}
