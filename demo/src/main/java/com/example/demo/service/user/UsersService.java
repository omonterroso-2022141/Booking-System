package com.example.demo.service.user;

import com.example.demo.model.user.User;

import java.util.List;

public interface UsersService {
    User createUser(User user);
    List<User> getAllUsers();
    User getUserById(String id); // Cambia Long a String
    User updateUser(String id, User user); // Cambia Long a String
    void deleteUser(String id); // Cambia Long a String
}
