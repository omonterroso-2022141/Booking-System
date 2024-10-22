package com.example.demo.service.user;

import com.example.demo.repository.user.User;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

@Service
public class UsersServiceMap implements UsersService {
    private HashMap<Long, User> users = new HashMap<>();
    private Long currentId = 1L;

    @Override
    public User createUser(User user) {
        user.setId(currentId++);
        users.put(user.getId(), user);
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }

    @Override
    public User getUserById(Long id) {
        return users.get(id);
    }

    @Override
    public User updateUser(Long id, User user) {
        users.put(id, user);
        return user;
    }

    @Override
    public void deleteUser(Long id) {
        users.remove(id);
    }
}
