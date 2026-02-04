package com.example.auth_reference.controller;

import com.example.auth_reference.dto.LoginRequestDto;
import com.example.auth_reference.dto.RegisterRequestDto;
import com.example.auth_reference.dto.RegisterResponseDto;
import com.example.auth_reference.entity.UserInfo;
import com.example.auth_reference.security.JwtService;
import com.example.auth_reference.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public Map<String, String> login(@RequestBody @Valid LoginRequestDto dto) {
        return userService.login(dto);
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
    public RegisterResponseDto register(@RequestBody @Valid RegisterRequestDto requestDto) {
        return userService.register(requestDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public String admin() {
        return "Admin access";
    }
}
