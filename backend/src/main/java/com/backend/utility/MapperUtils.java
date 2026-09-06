package com.backend.utility;

import com.backend.model.Claim;
import com.backend.model.Policy;
import com.backend.model.User;
import com.backend.model.dto.*;

import java.time.LocalDate;
import java.util.Objects;

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
                policy.getStatus(),
                (Objects.isNull(policy.getUser()) ? null : policy.getUser().getId())
        );
    }

    public static Policy mapPolicyRequestDTOtoEntity(PolicyRequestDTO policy) {
        return Policy.builder()
                .coverageAmount(policy.coverageAmount())
                .policyType(policy.policyType())
                .startDate(policy.startDate())
                .endDate(policy.endDate())
                .premium(policy.premium())
                .status("ACTIVE")
                .build();
    }

    public static Claim mapClaimRequestDTOtoEntity(ClaimRequestDTO claim) {
        return Claim.builder()
                .amount(claim.amount())
                .description(claim.description())
                .createdAt(LocalDate.now())
                .updatedAt(LocalDate.now())
                .status(claim.status())
                .build();
    }

    public static ClaimResponseDTO mapClaimEntityToResponseDTO(Claim savedClaim) {
        return new ClaimResponseDTO(savedClaim.getId(),
                savedClaim.getUser().getId(),
                savedClaim.getPolicy().getId(),
                savedClaim.getDescription(),
                savedClaim.getAmount(),
                savedClaim.getStatus(),
                savedClaim.getCreatedAt(),
                savedClaim.getUpdatedAt());
    }
}
