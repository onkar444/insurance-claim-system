package com.backend.utility;

import com.backend.model.Policy;
import com.backend.model.User;
import com.backend.model.dto.*;

import java.util.Objects;

public class PolicyMapper {


    public static PolicyResponseDTO entityToResponseDTO(Policy policy) {
        return new PolicyResponseDTO(
                policy.getId(),
                policy.getCustomer().getId(),
                policy.getPolicyType(),
                policy.getCoverageAmount(),
                policy.getPremium(),
                policy.getStartDate(),
                policy.getEndDate(),
                policy.getStatus()
        );
    }

    public static Policy requestDTOtoEntity(PolicyRequestDTO policy) {
        return Policy.builder()
                .coverageAmount(policy.coverageAmount())
                .policyType(policy.policyType())
                .startDate(policy.startDate())
                .endDate(policy.endDate())
                .premium(policy.premium())
                .build();
    }
}
