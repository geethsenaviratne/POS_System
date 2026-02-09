package com.zosh.controller;

import com.zosh.exceptions.UserException;
import com.zosh.mapper.UserMapper;
import com.zosh.model.User;
import com.zosh.payload.dto.UserDto;
import com.zosh.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<UserDto> getUserProfile(
            @RequestHeader("Authorization") String jwt
    ) throws UserException {
        User user = userService.getUserFromJwtToken(jwt);
        return ResponseEntity.ok(UserMapper.toDto(user));
    }



    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id
    ) throws UserException, Exception {
        User user = userService.getUserById(id);
        if (user == null) {
            throw new UserException("User not found");
        }
        return ResponseEntity.ok(UserMapper.toDto(user));
    }

}
