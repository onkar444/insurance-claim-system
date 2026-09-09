package com.backend.model.dto;

import java.time.LocalDate;

public record PolicyResponseDTO(
        Long id,
        Long customerId,
        String policyType,
        Double premium,
        Double coverageAmount,
        LocalDate startDate,
        LocalDate endDate,
        String status
) {}
