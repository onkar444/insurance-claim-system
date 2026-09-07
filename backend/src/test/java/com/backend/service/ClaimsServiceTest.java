package com.backend.service;

import com.backend.model.Claim;
import com.backend.model.Policy;
import com.backend.model.User;
import com.backend.model.dto.ClaimRequestDTO;
import com.backend.repository.ClaimRepositroy;
import com.backend.repository.PolicyRepository;
import com.backend.repository.UserRepository;
import com.backend.repository.exception.ClaimNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.Assert;

import java.time.LocalDate;
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
                                .id(1).build())
                        .user(User.builder().id(1).build())
                        .createdAt(LocalDate.now())
                        .updatedAt(LocalDate.now())
                        .status("ACTIVE")
                        .build()
        );

        when(policyRepository.findById(any(Integer.class))).thenReturn(
                Optional.ofNullable(Policy.builder()
                        .id(1)
                        .build())
        );

        when(userRepository.findById(any(Integer.class))).thenReturn(
                Optional.ofNullable(User.builder()
                        .id(1)
                        .build())
        );

        var result = claimsService.save(new ClaimRequestDTO("Claim", 10000.0,1,"ACTIVE"));

        Assert.notNull(result, "Result must not be empty");
        Assert.hasLength(result.description(), "Description must have length");

    }

    @Test
    void findById() {
        var claimRequest = Claim.builder()
                .id(10)
                .description("claim")
                .amount(10000.0)
                .createdAt(LocalDate.now())
                .updatedAt(LocalDate.now())
                .status("ACCEPTED")
                .build();
        when(claimRepositroy.findById(10)).thenReturn(Optional.ofNullable(claimRequest));

        var result = claimsService.findById(10);

        Assert.notNull(result, "Result must not be empty");
        Assert.isTrue(result.id().equals(10), "Id must match");
    }

    @Test
    void findByIdNotFound() {

        when(claimRepositroy.findById(100)).thenThrow(new ClaimNotFoundException("Claim not found"));
        Assertions.assertThrows(ClaimNotFoundException.class,
                () -> claimsService.findById(100), "Should throw exception");
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