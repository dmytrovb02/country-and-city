package com.techtask.controller;

import com.techtask.dto.user.UserLoginRequestDto;
import com.techtask.dto.user.UserLoginResponseDto;
import com.techtask.dto.user.UserRegistrationRequestDto;
import com.techtask.dto.user.UserResponseDto;
import com.techtask.security.AuthenticationService;
import com.techtask.service.impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication manager",
        description = "Endpoints for authorisation and authentication")
public class AuthenticationController {
    private final UserServiceImpl userService;
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    @Operation(summary = "Registration", description = "Registration a new user")
    public UserResponseDto register(@RequestBody @Valid UserRegistrationRequestDto requestDto) {
        return userService.register(requestDto);
    }

    @PostMapping("/login")
    @Operation(summary = "Authentication", description = "User authentication")
    public UserLoginResponseDto login(@RequestBody @Valid UserLoginRequestDto requestDto) {
        return authenticationService.authenticate(requestDto);
    }
}
