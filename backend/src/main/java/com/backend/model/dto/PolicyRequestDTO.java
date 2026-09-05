package com.backend.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record PolicyRequestDTO(
        @NotBlank String policyType,
        @Positive Double premium,
        @Positive Double coverageAmount,
        @NotNull LocalDate startDate,
        @NotNull LocalDate endDate
) {}
