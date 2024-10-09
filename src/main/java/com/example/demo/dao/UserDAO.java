package com.example.demo.dao;

import com.example.demo.models.Role;
import com.example.demo.models.User;

import java.util.List;

public interface UserDAO {
    List<User> findAll();
    User findOne(Long id);
    void save(User user);
    void delete(Long id);
    void update(Long id, User user);
    void saveRole(Role role);
    Role findRoleByName(String roleName);
    User findByUsername(String username);
    void saveUserWithRoles(User user); // добавляем метод
}
