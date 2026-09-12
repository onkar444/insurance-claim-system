package com.backend.model.dto;

import com.backend.model.POLICY_STATUS;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record PolicyRequestDTO(
        @NotBlank Long customerId,
        @NotBlank String policyType,
        @Positive Double premium,
        @Positive Double coverageAmount,
        @NotNull LocalDate startDate,
        @NotNull LocalDate endDate,
        @NotBlank POLICY_STATUS status
) {}
