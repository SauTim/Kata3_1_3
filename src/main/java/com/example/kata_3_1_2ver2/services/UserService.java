package com.example.kata_3_1_2ver2.services;

import com.example.kata_3_1_2ver2.entities.User;

import java.util.List;

public interface UserService {
    User findByUsername(String username);
    List<User> getAllUsers();
    void saveUser(User user);
    User getUserById(Long id);
    void deleteUserById(Long id);
}
