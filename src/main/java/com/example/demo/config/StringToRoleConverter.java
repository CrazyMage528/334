package com.example.demo.config;

import com.example.demo.models.Role;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToRoleConverter implements Converter<String, Role> {
    @Override
    public Role convert(String source) {
        Role role = new Role();
        role.setName(source);
        return role;
    }
}
