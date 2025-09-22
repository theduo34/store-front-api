package com.theduo.storefront.user.service;

import com.theduo.storefront.auth.dto.RegisterUserRequest;
import com.theduo.storefront.common.exception.ExistByEmailException;
import com.theduo.storefront.user.dto.UserDto;
import com.theduo.storefront.user.mapper.UserMapper;
import com.theduo.storefront.user.repo.GroupTypeRepository;
import com.theduo.storefront.user.repo.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final GroupTypeRepository groupTypeRepository;

    public UserDto register(RegisterUserRequest request) {
        if(userRepository.existsByEmail(request.getEmail())) {
            throw new ExistByEmailException();
        }

        var user = userMapper.toRegisterDto(request);
        user.setGroupType(groupTypeRepository.findById(1L).orElseThrow());
        userRepository.save(user);

        return userMapper.toUserDto(user);
    }

}
