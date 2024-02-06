package com.techtask.service.impl;

import com.techtask.dto.user.UserRegistrationRequestDto;
import com.techtask.dto.user.UserResponseDto;
import com.techtask.model.User;
import com.techtask.exception.EntityNotFoundException;
import com.techtask.exception.RegistrationException;
import com.techtask.mapper.UserMapper;
import com.techtask.repository.user.UserRepository;
import com.techtask.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDto register(UserRegistrationRequestDto requestDto) {
        if (userRepository.findByEmail(requestDto.getEmail()).isPresent()) {
            throw new RegistrationException("Email already registered");
        }
        User newUser = userMapper.mapToModel(requestDto);
        newUser.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        userRepository.save(newUser);

        return userMapper.mapToDto(newUser);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(
                        () -> new EntityNotFoundException("Can't find user by email: " + email));
    }
}
