package com.karwowski.auth.service;

import com.karwowski.auth.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
    List<User> findAllUsers();
    User findById(Long id);
}