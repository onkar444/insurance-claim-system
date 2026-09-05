package com.backend.service;

import com.backend.repository.exception.ClaimNotFoundException;
import com.backend.model.Claim;
import com.backend.model.dto.ClaimRequestDTO;
import com.backend.model.dto.ClaimResponseDTO;
import com.backend.repository.ClaimRepositroy;
import com.backend.utility.MapperUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClaimsService {
    private final ClaimRepositroy claimsRepository;

    public ClaimsService(ClaimRepositroy claimsRepository) {
        this.claimsRepository = claimsRepository;
    }


    public ClaimResponseDTO save(ClaimRequestDTO claim) {
        var savedClaim =  claimsRepository.save(MapperUtils.mapClaimRequestDTOtoEntity(claim));
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
