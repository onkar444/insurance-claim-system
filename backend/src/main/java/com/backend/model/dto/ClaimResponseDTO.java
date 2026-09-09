package com.backend.model.dto;

import com.backend.model.CLAIM_STATUS;

import java.time.LocalDateTime;

public record ClaimResponseDTO(
        Long id,
        Long customerId,
        Long adjusterId,
        Long policyId,
        String description,
        Double amount,
        CLAIM_STATUS status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}
