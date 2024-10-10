package com.example.demo.services;

import com.example.demo.models.Role;
import com.example.demo.models.User;
import jakarta.transaction.Transactional;

import java.util.List;

public interface UsersService {
    List<User> findAll();
    User findOne(Long id);
    User findByUsername(String username);

    @Transactional
    User findByEmail(String email);

    Role findRoleByName(String roleName);
    void saveRole(Role role);
    void createUser(User user);
    void updateUser(Long id, User updatedUser);
    void deleteUser(Long id);
    void save(User user);
}
