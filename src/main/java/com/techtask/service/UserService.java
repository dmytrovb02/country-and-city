package com.techtask.service;

import com.techtask.dto.user.UserRegistrationRequestDto;
import com.techtask.dto.user.UserResponseDto;

public interface UserService {

    UserResponseDto register(UserRegistrationRequestDto requestDto);
}
