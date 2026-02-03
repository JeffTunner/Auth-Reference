package com.example.auth_reference.service;

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

    public UserInfo register(String username, String password) {
        String hashedPassword = passwordEncoder.encode(password);
        UserInfo user = new UserInfo();
        user.setUsername(username);
        user.setPassword(hashedPassword);
        return userRepository.save(user);
    }

    public Map<String, String> login(String username, String password) {
        UserInfo user = userRepository.findByUsername(username);
        if(user == null) {
            throw new NullPointerException("User not found!");
        }
        if(passwordEncoder.matches(password, user.getPassword())) {
            List<String> roles = List.of("ROLE_USER");
            String token = jwtService.generateToken(username, roles);
            return Map.of("token", token);
        } else {
            throw new IllegalStateException("Password doesnt match");
        }
    }
}
