package com.backend.model.dto;

import java.time.LocalDate;

public record ClaimResponseDTO(
        Integer id,
        String description,
        Double amount,
        String status,
        LocalDate createdAt,
        LocalDate updatedAt
) {}
