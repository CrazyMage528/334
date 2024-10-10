package com.example.demo.controllers;

import com.example.demo.models.Role;
import com.example.demo.models.User;
import com.example.demo.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UsersService usersService;

    @Autowired
    public AdminController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping
    public String adminHome(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("currentUser", user);
        model.addAttribute("users", usersService.findAll());
        model.addAttribute("user", new User());
        return "adminHome";
    }

    @PostMapping("/users")
    public String createUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("users", usersService.findAll());
            return "adminHome";
        }
        usersService.createUser(user);  // Перенесли логику в сервисный слой
        return "redirect:/admin";
    }

    @PostMapping("/users/update/{id}")
    public String updateUser(@PathVariable("id") Long id, @ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "editUser";
        }
        usersService.updateUser(id, user);  // Перенесли логику в сервисный слой
        return "redirect:/admin";
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        usersService.deleteUser(id);  // Перенесли логику в сервисный слой
        return "redirect:/admin";
    }
}
