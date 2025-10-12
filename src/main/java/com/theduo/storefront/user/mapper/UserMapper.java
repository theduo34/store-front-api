package com.theduo.storefront.user.mapper;

import com.theduo.storefront.auth.dto.RegisterUserRequest;
import com.theduo.storefront.user.dto.UpdateUserRequest;
import com.theduo.storefront.user.dto.UserDto;
import com.theduo.storefront.user.entity.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "role", source = "groupType.role")
    UserDto toUserDto(User user);

    User toRegisterDto(RegisterUserRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "uuid", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void toUpdateDto(UpdateUserRequest request, @MappingTarget User user);

}
