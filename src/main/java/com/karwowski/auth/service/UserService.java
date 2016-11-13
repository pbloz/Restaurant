package com.karwowski.auth.service;

import com.karwowski.auth.model.User;

import java.util.List;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
    List<User> findAllUsers();
}