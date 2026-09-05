package com.backend.model.dto;

import java.time.LocalDate;

public record PolicyResponseDTO(
        Integer id,
        String policyType,
        Double premium,
        Double coverageAmount,
        LocalDate startDate,
        LocalDate endDate,
        String status
) {}
