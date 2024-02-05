package com.techtask.mapper;

import com.techtask.dto.user.UserRegistrationRequestDto;
import com.techtask.dto.user.UserResponseDto;
import com.techtask.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponseDto mapToDto(User user);

    User mapToModel(UserRegistrationRequestDto requestDto);
}
