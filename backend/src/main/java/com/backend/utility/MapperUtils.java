package com.backend.utility;

import com.backend.model.Claim;
import com.backend.model.Policy;
import com.backend.model.User;
import com.backend.model.dto.*;

public class MapperUtils {

    public static UserResponseDTO mapUserEntityToResponseDTO(User user) {
        return new UserResponseDTO(user.getId(), user.getName(), user.getRole(), user.getEmail());

    }

    public static User mapUserRequestDTOtoEntity(UserRequestDTO userRequestDTO) {
        return User.builder()
                .email(userRequestDTO.email())
                .role(userRequestDTO.role())
                .name(userRequestDTO.name())
                .build();
    }

    public static PolicyResponseDTO mapPolicyEntityToDto(Policy policy) {
        return new PolicyResponseDTO(
                policy.getId(),
                policy.getPolicyType(),
                policy.getCoverageAmount(),
                policy.getPremium(),
                policy.getStartDate(),
                policy.getEndDate(),
                policy.getStatus()
        );
    }

    public static Policy mapPolicyRequestDTOtoEntity(PolicyRequestDTO policy) {
        return Policy.builder()
                .coverageAmount(policy.coverageAmount())
                .policyType(policy.policyType())
                .startDate(policy.startDate())
                .endDate(policy.endDate())
                .premium(policy.premium())
                .build();
    }

    public static Claim mapClaimRequestDTOtoEntity(ClaimRequestDTO claim) {
        return Claim.builder()
                .amount(claim.amount())
                .description(claim.description())
                .build();
    }

    public static ClaimResponseDTO mapClaimEntityToResponseDTO(Claim savedClaim) {
        return new ClaimResponseDTO(savedClaim.getId(),
                savedClaim.getDescription(),
                savedClaim.getAmount(),
                savedClaim.getStatus(),
                savedClaim.getCreatedAt(),
                savedClaim.getUpdatedAt());
    }
}
