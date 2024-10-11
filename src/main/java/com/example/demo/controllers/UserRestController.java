package com.example.demo.controllers;

import com.example.demo.models.User;
import com.example.demo.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserRestController {

    private final UsersService usersService;

    @Autowired
    public UserRestController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return usersService.findAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return usersService.findOne(id);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        usersService.createUser(user);
        return user;
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        usersService.updateUser(id, user);
        return user;
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        usersService.deleteUser(id);
    }
}
