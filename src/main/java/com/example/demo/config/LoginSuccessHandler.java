package com.example.demo.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        String role = authentication.getAuthorities().stream().findFirst().get().getAuthority();
        System.out.println("Пользователь успешно вошел с ролью: " + role);

        if (role.equals("ROLE_ADMIN")) {
            System.out.println("Перенаправление на /admin");
            response.sendRedirect(request.getContextPath() + "/admin");
        } else if (role.equals("ROLE_USER")) {
            System.out.println("Перенаправление на /user");
            response.sendRedirect(request.getContextPath() + "/user");
        }
    }
}




