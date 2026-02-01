package com.example.auth_reference.security;

import java.util.List;

public record AuthUser(
        String username,
        List<String> roles
) {}
