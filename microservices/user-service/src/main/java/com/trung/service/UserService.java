package com.trung.service;

import com.trung.exception.UserException;
import com.trung.modal.User;

import java.util.List;

public interface UserService {
    User createUser(User user);
    User getUserById(Long id) throws UserException;
    List<User> getAllUsers();
    void deleteUserById(Long id) throws Exception;
    User updateUser(Long id, User user) throws Exception;
}
