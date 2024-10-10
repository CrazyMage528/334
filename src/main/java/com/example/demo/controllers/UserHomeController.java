package com.example.demo.controllers;

import com.example.demo.models.User;
import com.example.demo.services.UsersService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserHomeController {

    private final UsersService usersService;

    @Autowired
    public UserHomeController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping
    public String userHome(Model model, Principal principal) {
        User user = usersService.findByUsername(principal.getName());
        Hibernate.initialize(user.getRoles()); // Инициализируем роли
        model.addAttribute("user", user);
        return "userHome";
    }

}
