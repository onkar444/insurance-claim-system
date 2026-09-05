package com.backend.model.dto;

public record UserResponseDTO(
        Integer id,
        String name,
        String role,
        String email
) {
}
