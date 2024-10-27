package com.example.demo;

import com.example.demo.model.user.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.user.UsersService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsersServiceTest {

    private UserRepository userRepository;
    private UsersService usersService;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        usersService = new UsersService(userRepository);
    }

    @Test
    void testCreateUser_Success() {
        User user = new User();
        user.setId("user1");
        user.setName("Test User");
        user.setEmail("test@example.com");

        when(userRepository.save(any(User.class))).thenReturn(user);

        User createdUser = usersService.createUser(user);

        assertNotNull(createdUser);
        assertEquals(user.getId(), createdUser.getId());
        verify(userRepository).save(user);
    }

    @Test
    void testGetUser_Success() {
        String userId = "user1";
        User user = new User();
        user.setId(userId);
        user.setName("Test User");

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        User foundUser = usersService.getUser(userId);

        assertNotNull(foundUser);
        assertEquals(userId, foundUser.getId());
        verify(userRepository).findById(userId);
    }

    @Test
    void testGetUser_NotFound() {
        String userId = "user1";
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        User foundUser = usersService.getUser(userId);

        assertNull(foundUser);
        verify(userRepository).findById(userId);
    }

    @Test
    void testGetAllUsers_Success() {
        User user1 = new User();
        user1.setId("user1");
        user1.setName("Test User 1");

        User user2 = new User();
        user2.setId("user2");
        user2.setName("Test User 2");

        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);

        when(userRepository.findAll()).thenReturn(users);

        List<User> foundUsers = usersService.getAllUsers();

        assertNotNull(foundUsers);
        assertEquals(2, foundUsers.size());
        verify(userRepository).findAll();
    }

    @Test
    void testUpdateUser_Success() {
        String userId = "user1";
        User existingUser = new User();
        existingUser.setId(userId);
        existingUser.setName("Old Name");
        existingUser.setEmail("old@example.com");

        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(User.class))).thenReturn(existingUser);

        User updatedUser = new User();
        updatedUser.setName("New Name");
        updatedUser.setEmail("new@example.com");

        User result = usersService.updateUser(userId, updatedUser);

        assertNotNull(result);
        assertEquals("New Name", result.getName());
        verify(userRepository).save(existingUser);
    }

    @Test
    void testUpdateUser_NotFound() {
        String userId = "user1";
        User updatedUser = new User();
        updatedUser.setName("New Name");
        updatedUser.setEmail("new@example.com");

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        User result = usersService.updateUser(userId, updatedUser);

        assertNull(result);
        verify(userRepository, never()).save(any());
    }

    @Test
    void testDeleteUser_Success() {
        String userId = "user1";
        doNothing().when(userRepository).deleteById(userId);

        usersService.deleteUser(userId);

        verify(userRepository).deleteById(userId);
    }

    @Test
    void testExistsByEmail_Success() {
        String email = "test@example.com";
        when(userRepository.existsByEmail(email)).thenReturn(true);

        boolean exists = usersService.existsByEmail(email);

        assertTrue(exists);
        verify(userRepository).existsByEmail(email);
    }

    @Test
    void testExistsByEmail_NotFound() {
        String email = "test@example.com";
        when(userRepository.existsByEmail(email)).thenReturn(false);

        boolean exists = usersService.existsByEmail(email);

        assertFalse(exists);
        verify(userRepository).existsByEmail(email);
    }
}
