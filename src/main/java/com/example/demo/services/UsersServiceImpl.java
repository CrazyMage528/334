package com.example.demo.services;

import com.example.demo.dao.UserDAO;
import com.example.demo.models.Role;
import com.example.demo.models.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import com.example.demo.dao.UserDAO;
import com.example.demo.models.Role;
import com.example.demo.models.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UsersServiceImpl implements UsersService {

    private final UserDAO userDAO;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsersServiceImpl(UserDAO userDAO, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void save(User user) {
        if (user.getPassword() != null && !user.getPassword().startsWith("$2a$")) {
            user.setOriginalPassword(user.getPassword());
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userDAO.save(user);
    }

    @Override
    public List<User> findAll() {
        return userDAO.findAll();
    }

    @Override
    public User findOne(Long id) {
        return userDAO.findOne(id);
    }

    @Override
    public User findByUsername(String username) {
        return userDAO.findByUsername(username);
    }

    @Override
    public Role findRoleByName(String roleName) {
        return userDAO.findRoleByName(roleName);
    }

    @Override
    @Transactional
    public void saveRole(Role role) {
        userDAO.saveRole(role);
    }

    @Override
    @Transactional
    public void createUser(User user) {
        Role userRole = findRoleByName("ROLE_USER");
        if (userRole == null) {
            userRole = new Role();
            userRole.setName("ROLE_USER");
            saveRole(userRole);
        }

        Role adminRole = findRoleByName("ROLE_ADMIN");
        if (adminRole == null) {
            adminRole = new Role();
            adminRole.setName("ROLE_ADMIN");
            saveRole(adminRole);
        }

        Set<Role> selectedRoles = new HashSet<>();
        for (Role role : user.getRoles()) {
            if (role.getName().equals("ROLE_ADMIN")) {
                selectedRoles.add(adminRole);
            } else if (role.getName().equals("ROLE_USER")) {
                selectedRoles.add(userRole);
            }
        }
        user.setRoles(selectedRoles);
        save(user);
    }

    @Override
    @Transactional
    public void updateUser(Long id, User updatedUser) {
        User existingUser = userDAO.findOne(id);

        existingUser.setName(updatedUser.getName());
        existingUser.setLastName(updatedUser.getLastName());
        existingUser.setAge(updatedUser.getAge());
        existingUser.setEmail(updatedUser.getEmail());

        if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
            existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        }

        existingUser.setRoles(updatedUser.getRoles());
        userDAO.update(id, existingUser);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        User user = userDAO.findOne(id);
        if (user != null) {
            user.getRoles().clear(); // Удаляем все роли у пользователя перед удалением
            userDAO.update(id, user);
            userDAO.delete(id); // Теперь удаляем пользователя
        }
    }

}
