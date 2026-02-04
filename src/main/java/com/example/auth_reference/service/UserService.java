package com.example.auth_reference.service;

import com.example.auth_reference.dto.LoginRequestDto;
import com.example.auth_reference.dto.RegisterRequestDto;
import com.example.auth_reference.dto.RegisterResponseDto;
import com.example.auth_reference.entity.UserInfo;
import com.example.auth_reference.repository.UserRepository;
import com.example.auth_reference.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtService jwtService;

    public RegisterResponseDto register(RegisterRequestDto requestDto) {
        if(userRepository.existsByUsername(requestDto.username())) {
            throw new IllegalStateException("Username Already Exists!");
        }
        String hashedPassword = passwordEncoder.encode(requestDto.password());
        UserInfo user = new UserInfo();
        user.setUsername(requestDto.username());
        user.setPassword(hashedPassword);
        List<String > roles = (requestDto.roles() == null || requestDto.roles().isEmpty()) ? List.of("ROLE_USER") : requestDto.roles();
        user.setRoles(roles);
        UserInfo saved = userRepository.save(user);
        return new RegisterResponseDto(saved.getUsername(), saved.getRoles());
    }

    public Map<String, String> login(LoginRequestDto dto) {
        UserInfo user = userRepository.findByUsername(dto.username());
        if(user == null) {
            throw new IllegalStateException("Invalid Credentials");
        }
        if(passwordEncoder.matches(dto.password(), user.getPassword())) {
            String token = jwtService.generateToken(user.getUsername(), user.getRoles());
            return Map.of("token", token);
        } else {
            throw new IllegalStateException("Invalid Credentials");
        }
    }
}
