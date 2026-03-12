package org.zin.com.phoneshopapi.mapper;

import org.mapstruct.Mapper;
import org.zin.com.phoneshopapi.dto.response.UserResponse;
import org.zin.com.phoneshopapi.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponse toDTO(User user);

}
