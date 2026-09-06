package com.backend.model.dto;

import java.time.LocalDate;

public record ClaimResponseDTO(
        Integer id,
        Integer userId,
        Integer policyId,
        String description,
        Double amount,
        String status,
        LocalDate createdAt,
        LocalDate updatedAt
) {}
