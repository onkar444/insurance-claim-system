package com.backend.model.dto;

import com.backend.model.ROLE;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record UserRequestDTO(
        @NotBlank String name,
        @Email String email,
        @NotBlank String password,
        @NotBlank List<ROLE> roles
) {}
