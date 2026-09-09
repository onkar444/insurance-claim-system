package com.backend.model.dto;

import com.backend.model.ROLE;
import jakarta.annotation.Nullable;

import java.util.List;

public record UserResponseDTO(
        Long id,
        String name,
        @Nullable List<ROLE> role,
        String email
) {
}
