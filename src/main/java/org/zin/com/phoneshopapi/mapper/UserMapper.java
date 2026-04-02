package org.zin.com.phoneshopapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.zin.com.phoneshopapi.dto.request.RegisterRequest;
import org.zin.com.phoneshopapi.dto.response.UserResponse;
import org.zin.com.phoneshopapi.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {



//    @Mapping(target = "role", source = "role")
//    UserResponse toDTO(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", constant = "ROLE_USER")
    User toEntity(RegisterRequest request);

    UserResponse toDto(User user);





}
