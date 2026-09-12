package com.backend.service;

import com.backend.model.CLAIM_STATUS;
import com.backend.model.Claim;
import com.backend.model.Policy;
import com.backend.model.User;
import com.backend.model.dto.ClaimRequestDTO;
import com.backend.repository.ClaimRepositroy;
import com.backend.repository.PolicyRepository;
import com.backend.repository.UserRepository;
import com.backend.exception.ClaimNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClaimsServiceTest {

    @Mock
    ClaimRepositroy claimRepositroy;

    @InjectMocks
    ClaimsService claimsService;
    @Mock
    private PolicyRepository policyRepository;
    @Mock
    private UserRepository userRepository;

    @Test
    void save() {

        when(claimRepositroy.save(any(Claim.class))).thenReturn(
                Claim.builder()
                        .description("Claim")
                        .amount(12000.0)
                        .policy(Policy.builder()
                                .id(1L).build())
                        .customer(User.builder().id(1L).build())
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .status(CLAIM_STATUS.SUBMITTED)
                        .build()
        );

        when(policyRepository.findById(any(Long.class))).thenReturn(
                Optional.ofNullable(Policy.builder()
                        .id(1L)
                        .build())
        );

        when(userRepository.findById(any(Long.class))).thenReturn(
                Optional.ofNullable(User.builder()
                        .id(1L)
                        .build())
        );

        var result = claimsService.save(new ClaimRequestDTO("Claim", 10000.0,1L,1L,"ACTIVE"));

        Assert.notNull(result, "Result must not be empty");
        Assert.hasLength(result.description(), "Description must have length");

    }

    @Test
    void findById() {
        var claimRequest = Claim.builder()
                .id(10L)
                .description("claim")
                .amount(10000.0)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .status(CLAIM_STATUS.SUBMITTED)
                .build();
        when(claimRepositroy.findById(10L)).thenReturn(Optional.ofNullable(claimRequest));

        var result = claimsService.findById(10L);

        Assert.notNull(result, "Result must not be empty");
        Assert.isTrue(result.id().equals(10L), "Id must match");
    }

    @Test
    void findByIdNotFound() {

        when(claimRepositroy.findById(100L)).thenThrow(new ClaimNotFoundException("Claim not found"));
        Assertions.assertThrows(ClaimNotFoundException.class,
                () -> claimsService.findById(100L), "Should throw exception");
    }

    @Test
    void getAllClaims() {
        when(claimRepositroy.findAll()).thenReturn(List.of(Claim.builder()
                .description("Claim")
                .amount(12000.0)
                .build(), Claim.builder()
                .description("Claim")
                .amount(12000.0)
                .build()));

        var result = claimsService.getAllClaims();

        Assert.notNull(result, "Result must not be null");
        Assert.isTrue(Objects.equals(2, result.size()), "Size must match");
    }

}