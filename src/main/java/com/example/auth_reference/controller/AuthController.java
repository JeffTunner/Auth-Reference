package com.example.auth_reference.controller;

import com.example.auth_reference.entity.UserInfo;
import com.example.auth_reference.security.JwtService;
import com.example.auth_reference.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    UserService userService;

    private final JwtService jwtService;

    public AuthController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    //LOGIN (Generate Token)
    @PostMapping("/login")
    public Map<String, String> login(@RequestParam String username, @RequestParam String password) {
        return userService.login(username, password);
    }

    //WHOAMI (Requires JWT)
    @GetMapping("/me")
    public Map<String, Object> me(Authentication authentication) {
        return Map.of(
                "username", authentication.getPrincipal(),
                "roles", authentication.getAuthorities()
        );
    }

    @PostMapping("/register")
    public UserInfo register(@RequestParam String username, @RequestParam String password) {
        return userService.register(username, password);
    }
}
