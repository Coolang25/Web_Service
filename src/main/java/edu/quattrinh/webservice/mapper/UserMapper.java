package edu.quattrinh.webservice.mapper;

import edu.quattrinh.webservice.dto.request.UserCreationRequest;
import edu.quattrinh.webservice.dto.request.UserUpdateRequest;
import edu.quattrinh.webservice.dto.response.UserResponse;
import edu.quattrinh.webservice.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);

    //@Mapping(source = "firstName", target = "lastName")
    //@Mapping(target = "lastName", ignore = true)
    UserResponse toUserResponse(User user);
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
