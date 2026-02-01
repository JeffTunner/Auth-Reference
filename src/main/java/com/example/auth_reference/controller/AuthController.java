package com.example.auth_reference.controller;

import com.example.auth_reference.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtService jwtService;

    public AuthController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    //LOGIN (Generate Token)
    @PostMapping("/login")
    public Map<String, String> login(@RequestParam String username) {
        List<String> roles = List.of("ROLE_USER");
        String token = jwtService.generateToken(username, roles);
        return Map.of("token", token);
    }

    //WHOAMI (Requires JWT)
    @GetMapping("/me")
    public Map<String, Object> me(Authentication authentication) {
        return Map.of(
                "username", authentication.getPrincipal(),
                "roles", authentication.getAuthorities()
        );
    }
}
