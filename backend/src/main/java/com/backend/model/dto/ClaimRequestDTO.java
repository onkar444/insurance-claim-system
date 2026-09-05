package com.backend.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record ClaimRequestDTO(
        @NotBlank String description,
        @Positive Double amount
) {
}
