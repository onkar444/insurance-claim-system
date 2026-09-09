package com.backend.utility;

import com.backend.model.Claim;
import com.backend.model.dto.ClaimRequestDTO;
import com.backend.model.dto.ClaimResponseDTO;

public class ClaimsMapper {


    public static Claim requestDTOtoEntity(ClaimRequestDTO claim) {
        return Claim.builder()
                .amount(claim.amount())
                .description(claim.description())
                .build();
    }

    public static ClaimResponseDTO entityToResponseDTO(Claim claim) {
        return new ClaimResponseDTO(claim.getId(),
                claim.getCustomer().getId(),
                null != claim.getAdjuster() ? claim.getAdjuster().getId():null ,
                claim.getPolicy().getId(),
                claim.getDescription(),
                claim.getAmount(),
                claim.getStatus(),
                claim.getCreatedAt(),
                claim.getUpdatedAt());
    }
}
