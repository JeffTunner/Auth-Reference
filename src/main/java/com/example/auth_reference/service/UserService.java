package com.example.auth_reference.service;

import com.example.auth_reference.entity.UserInfo;
import com.example.auth_reference.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public UserInfo register(String username, String password) {
        String hashedPassword = passwordEncoder.encode(password);
        UserInfo user = new UserInfo();
        user.setUsername(username);
        user.setPassword(hashedPassword);
        return userRepository.save(user);
    }
}
