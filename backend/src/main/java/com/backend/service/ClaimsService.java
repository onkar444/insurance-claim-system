package com.backend.service;

import com.backend.model.CLAIM_STATUS;
import com.backend.model.Claim;
import com.backend.model.ROLE;
import com.backend.model.User;
import com.backend.model.dto.ClaimRequestDTO;
import com.backend.model.dto.ClaimResponseDTO;
import com.backend.repository.ClaimRepositroy;
import com.backend.repository.PolicyRepository;
import com.backend.repository.UserRepository;
import com.backend.exception.ClaimNotFoundException;
import com.backend.exception.PolicyNotFoundException;
import com.backend.exception.UserNotFoundException;
import com.backend.utility.ClaimsMapper;
import jakarta.transaction.Transactional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClaimsService {
    private final ClaimRepositroy claimsRepository;
    private final PolicyRepository policyRepository;
    private final UserRepository userRepository;

    public ClaimsService(ClaimRepositroy claimsRepository, PolicyRepository policyRepository, UserRepository userRepository) {
        this.claimsRepository = claimsRepository;
        this.policyRepository = policyRepository;
        this.userRepository = userRepository;
    }


    public ClaimResponseDTO save(ClaimRequestDTO claim) {
        var policyEntity = policyRepository.findById(claim.policyId())
                .orElseThrow(() -> new PolicyNotFoundException("Policy not found with Id::" + claim.policyId()));

        var userEntity = userRepository.findById(policyEntity.getCustomer().getId())
                .orElseThrow(() -> new UserNotFoundException("User not found with Id::" + policyEntity.getCustomer().getId()));

        var claimEntity = ClaimsMapper.requestDTOtoEntity(claim);

        claimEntity.setPolicy(policyEntity);
        claimEntity.setCustomer(userEntity);
        claimEntity.setStatus(CLAIM_STATUS.SUBMITTED);

        var savedClaim = claimsRepository.save(claimEntity);
        return ClaimsMapper.entityToResponseDTO(savedClaim);
    }

    public ClaimResponseDTO findById(Long id) {
        var claim = claimsRepository.findById(id).
                orElseThrow(() -> new ClaimNotFoundException("Cannot find claim with Id::" + id));

        return ClaimsMapper.entityToResponseDTO(claim);
    }

    public List<ClaimResponseDTO> getAllClaims() {
        return claimsRepository.findAll().stream()
                .filter(claim -> !claim.isDeleted())
                .map(ClaimsMapper::entityToResponseDTO)
                .toList();
    }

    public ClaimResponseDTO updateClaim(ClaimRequestDTO claim, Long id) {
        var existingClaim = claimsRepository.findById(id)
                .orElseThrow(() -> new ClaimNotFoundException("Claim not found with Id::" + id));

        existingClaim.setAmount(claim.amount());
        existingClaim.setDescription(claim.description());

        var savedClaim = claimsRepository.save(existingClaim);
        return ClaimsMapper.entityToResponseDTO(savedClaim);
    }

    public String deleteById(Long id) {
        Claim existingClaim = claimsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Claim not found with Id::" + id));
        existingClaim.setDeleted(Boolean.TRUE);
        claimsRepository.save(existingClaim);
        return "Claim deleted successfully";
    }

    public List<ClaimResponseDTO> getAllAssignedClaims(Authentication httpRequest) {
        Long currentUserId = getCurrentUserId(httpRequest);
        return claimsRepository.findByAdjusterId(currentUserId)
                .stream()
                .map(ClaimsMapper::entityToResponseDTO)
                .toList();
    }

    private Long getCurrentUserId(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        assert userDetails != null;
        var user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(()-> new UserNotFoundException("User not found exception"));
        return user.getId();
    }

    @Transactional
    public ClaimResponseDTO assignClaim(Long adjusterId, Long claimId) {
        Claim claim = claimsRepository.findById(claimId)
                .orElseThrow(()-> new ClaimNotFoundException("Claim not found with Id::"+claimId));

        User adjuster = userRepository.findById(adjusterId)
                .orElseThrow(()->  new UserNotFoundException("Adjuster not found with Id::"+adjusterId));


        if(!adjuster.getRoles().contains(ROLE.CLAIM_ADJUSTER)){
            throw new IllegalArgumentException("User is not claim adjuster");
        }

        if(!claim.getStatus().equals(CLAIM_STATUS.SUBMITTED)){
            throw new IllegalArgumentException("Only submitted claim can be adjusted");
        }

        claim.setAdjuster(adjuster);
        claim.setStatus(CLAIM_STATUS.ASSIGNED);

        return ClaimsMapper.entityToResponseDTO(claimsRepository.save(claim));
    }
}
