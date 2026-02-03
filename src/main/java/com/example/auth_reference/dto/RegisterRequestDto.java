package com.example.auth_reference.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

public record RegisterRequestDto(

        @NotBlank
        String username,

        @NotBlank
        @Size(min = 6)
        String password,

        List<String> roles
) {
}
