package com.theduo.storefront.user.service;

import com.theduo.storefront.auth.dto.RegisterUserRequest;
import com.theduo.storefront.common.exception.ExistByEmailException;
import com.theduo.storefront.user.dto.UserDto;
import com.theduo.storefront.user.mapper.UserMapper;
import com.theduo.storefront.user.repo.GroupTypeRepository;
import com.theduo.storefront.user.repo.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@AllArgsConstructor
@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final GroupTypeRepository groupTypeRepository;
    private final PasswordEncoder passwordEncoder;

    public UserDto register(RegisterUserRequest request) {
        if(userRepository.existsByEmail(request.getEmail())) {
            throw new ExistByEmailException();
        }

        var user = userMapper.toRegisterDto(request);
        user.setGroupType(groupTypeRepository.findById(1L).orElseThrow());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        return userMapper.toUserDto(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var user = userRepository.findUserByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("User not found with email: " + email ));

        return new User(
                user.getEmail(),
                user.getPassword(),
                Collections.emptyList()
        );

    }
}
