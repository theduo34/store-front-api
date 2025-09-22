package com.theduo.storefront.user.mapper;

import com.theduo.storefront.auth.dto.RegisterUserRequest;
import com.theduo.storefront.user.dto.UserDto;
import com.theduo.storefront.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "role", source = "groupType.role")
    UserDto toUserDto(User user);

    User toRegisterDto(RegisterUserRequest request);
}
