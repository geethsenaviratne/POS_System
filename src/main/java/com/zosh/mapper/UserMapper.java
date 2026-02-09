package com.zosh.mapper;

import com.zosh.model.User;
import com.zosh.payload.dto.UserDto;

public class UserMapper {

    public static UserDto toDto(User savedUser) {
        UserDto userDto = new UserDto();
        userDto.setId(savedUser.getId());
        userDto.setEmail(savedUser.getEmail());
        userDto.setFullName(savedUser.getFullName());
        userDto.setRole(savedUser.getRole());
        userDto.setPhone(savedUser.getPhone());
        userDto.setCreatedAt(savedUser.getCreatedAt());
        userDto.setUpdatedAt(savedUser.getUpdatedAt());
        userDto.setLastLogin(savedUser.getLastLogin());

        return userDto;
    }
}
