package com.example.demo.repository;

import com.example.demo.model.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    // Puedes agregar consultas personalizadas si es necesario
}
