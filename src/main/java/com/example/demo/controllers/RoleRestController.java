package com.example.demo.controllers;

import com.example.demo.models.Role;
import com.example.demo.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@CrossOrigin(origins = "*")
public class RoleRestController {

    private final UsersService usersService;

    @Autowired
    public RoleRestController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping
    public List<Role> getAllRoles() {
        return usersService.findAllRoles();
    }

    @GetMapping("/{id}")
    public Role getRoleById(@PathVariable Long id) {
        return usersService.findRoleById(id);
    }

    @PostMapping
    public Role createRole(@RequestBody Role role) {
        usersService.saveRole(role);
        return role;
    }

    @PutMapping("/{id}")
    public Role updateRole(@PathVariable Long id, @RequestBody Role role) {
        usersService.updateRole(id, role);
        return role;
    }

    @DeleteMapping("/{id}")
    public void deleteRole(@PathVariable Long id) {
        usersService.deleteRole(id);
    }
}
