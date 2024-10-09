package com.example.demo.services;


import com.example.demo.models.User;

import java.util.List;

public interface UsersService {

    List<User> findAll();

    User findOne(Long id);

    void save(User user);

    void delete(Long id);

    void update(Long id, User user);
}
