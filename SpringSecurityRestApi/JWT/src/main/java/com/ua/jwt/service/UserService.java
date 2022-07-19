package com.ua.jwt.service;

import com.ua.jwt.domain.User;

import java.util.List;

public interface UserService {
    User register (User user);
    List<User> getAll();
    User findByUsername(String username);
    User findById(Long id);
    void delete(Long id);
}
