package com.example.demo.service.user;

import com.example.demo.repository.UserRepository;
import com.example.demo.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceMap implements UsersService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user); // Guarda el usuario en MongoDB
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll(); // Obtiene todos los usuarios de MongoDB
    }

    @Override
    public User getUserById(String id) {
        return userRepository.findById(id).orElse(null); // Obtiene usuario por ID
    }

    @Override
    public User updateUser(String id, User user) {
        user.setId(id); // Asegúrate de que el id esté establecido
        return userRepository.save(user); // Actualiza el usuario en MongoDB
    }

    @Override
    public void deleteUser(String id) {
        userRepository.deleteById(id); // Elimina el usuario por ID
    }
}
