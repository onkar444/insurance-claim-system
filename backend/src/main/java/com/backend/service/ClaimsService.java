package com.backend.service;

import com.backend.repository.PolicyRepository;
import com.backend.repository.UserRepository;
import com.backend.repository.exception.ClaimNotFoundException;
import com.backend.model.Claim;
import com.backend.model.dto.ClaimRequestDTO;
import com.backend.model.dto.ClaimResponseDTO;
import com.backend.repository.ClaimRepositroy;
import com.backend.repository.exception.PolicyNotFoundException;
import com.backend.repository.exception.UserNotFoundException;
import com.backend.utility.MapperUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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
                .orElseThrow(()-> new PolicyNotFoundException("Policy not found with Id::"+claim.policyId()));

        var userEntity = userRepository.findById(policyEntity.getUser().getId())
                .orElseThrow(()->new UserNotFoundException("User not found with Id::"+policyEntity.getUser().getId()));

        var claimEntity = MapperUtils.mapClaimRequestDTOtoEntity(claim);

        claimEntity.setPolicy(policyEntity);
        claimEntity.setUser(userEntity);
        if(Objects.isNull(claimEntity.getStatus())){
            claimEntity.setStatus("ACTIVE");
        }

        var savedClaim =  claimsRepository.save(claimEntity);
        return MapperUtils.mapClaimEntityToResponseDTO(savedClaim);
    }

    public ClaimResponseDTO findById(Integer id) {
        var claim = claimsRepository.findById(id).
                orElseThrow(() -> new ClaimNotFoundException("Cannot find policy with Id::" + id));

        return MapperUtils.mapClaimEntityToResponseDTO(claim);
    }

    public List<ClaimResponseDTO> getAllClaims() {
        return claimsRepository.findAll().stream()
                .map(MapperUtils::mapClaimEntityToResponseDTO)
                .toList();
    }

    public ClaimResponseDTO updateClaim(ClaimRequestDTO claim, Integer id) {
        var existingClaim = claimsRepository.findById(id)
                .orElseThrow(() -> new ClaimNotFoundException("Claim not found with Id::" + id));

        existingClaim.setAmount(claim.amount());
        existingClaim.setDescription(claim.description());

        var savedClaim = claimsRepository.save(existingClaim);
        return MapperUtils.mapClaimEntityToResponseDTO(savedClaim);
    }

    public String deleteById(Integer id) {
        Claim existingClaim = claimsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Claim not found with Id::" + id));
        existingClaim.setStatus("DELETED");
        claimsRepository.save(existingClaim);
        return "Claim deleted successfully";
    }
}
