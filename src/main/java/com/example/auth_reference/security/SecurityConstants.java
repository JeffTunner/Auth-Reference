package com.example.auth_reference.security;

public final class SecurityConstants {

    private SecurityConstants() {}

    // HTTP
    public static final String AUTH_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";

    // JWT Claims
    public static final String ROLES_CLAIM = "roles";
    public static final String USERNAME_CLAIM = "username";
}
