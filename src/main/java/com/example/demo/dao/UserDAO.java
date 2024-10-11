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
    Role findRoleByName(String roleName);
    User findByUsername(String username);
    void saveRole(Role role);
    void saveUserWithRoles(User user);
    User findByEmail(String email);
    List<Role> findAllRoles();
    Role findRoleById(Long id);
    void updateRole(Long id, Role role);
    void deleteRole(Long id);
}
