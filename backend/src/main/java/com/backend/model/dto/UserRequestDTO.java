package com.backend.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRequestDTO(@NotBlank String name, @Email String email,@NotBlank String password,@NotBlank String role) {
}
