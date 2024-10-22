package com.example.demo.service.user;

import com.example.demo.repository.user.User;

import java.util.List;

public interface UsersService {
    User createUser(User user);
    List<User> getAllUsers();
    User getUserById(Long id);
    User updateUser(Long id, User user);
    void deleteUser(Long id);
}
