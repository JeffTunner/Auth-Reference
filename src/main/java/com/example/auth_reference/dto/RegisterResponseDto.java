package com.example.auth_reference.dto;

import java.util.List;

public record RegisterResponseDto(
        String username,
        List<String> roles
) {
}
