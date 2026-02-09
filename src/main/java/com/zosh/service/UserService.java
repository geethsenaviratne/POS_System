package com.zosh.service;

import com.zosh.exceptions.UserException;
import com.zosh.model.User;

import java.util.List;

public interface UserService {

    User getUserFromJwtToken(String token) throws UserException;
    User getCurrentUser() throws UserException;
    User getUserFromEmail(String email) throws UserException;
    User getUserById(Long id) throws UserException, Exception;

    List<User> getAllUsers();
}
