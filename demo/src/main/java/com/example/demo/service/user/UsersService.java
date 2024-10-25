package com.example.demo.service.user;

import com.example.demo.model.user.User;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {

    private final UserRepository userRepository;

    public UsersService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User getUser(String id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(String id, User user) {
        User userExistente = userRepository.findById(id).orElse(null);
        if (userExistente != null) {
            userExistente.setName(user.getName());
            userExistente.setEmail(user.getEmail());
            return userRepository.save(userExistente);
        }
        return null;
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

    public boolean existsByEmail(String email){
        return userRepository.existsByEmail(email);
    }
}
