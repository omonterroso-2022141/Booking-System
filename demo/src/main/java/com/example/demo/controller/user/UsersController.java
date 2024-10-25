package com.example.demo.controller.user;

import com.example.demo.model.user.User;
import com.example.demo.service.user.UsersService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping("/create")
    public User createUsuario(@RequestBody User user) {
        return usersService.createUser(user);
    }

    @GetMapping("/find/{id}")
    public User getUser(@PathVariable String id) {
        return usersService.getUser(id);
    }

    @GetMapping("/list")
    public List<User> getAllUsers() {
        return usersService.getAllUsers();
    }

    @PutMapping("/update/{id}")
    public User updateUser(@PathVariable String id, @RequestBody User user) {
        return usersService.updateUser(id, user);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable String id) {
        usersService.deleteUser(id);
    }
}

