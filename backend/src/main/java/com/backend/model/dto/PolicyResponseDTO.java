package com.backend.model.dto;

import com.backend.model.POLICY_STATUS;

import java.time.LocalDate;

public record PolicyResponseDTO(
        Long id,
        Long customerId,
        String policyType,
        Double premium,
        Double coverageAmount,
        LocalDate startDate,
        LocalDate endDate,
        POLICY_STATUS status
) {}
