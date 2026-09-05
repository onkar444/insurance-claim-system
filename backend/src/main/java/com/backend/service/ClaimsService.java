package com.backend.service;

import com.backend.model.Claim;
import com.backend.repository.ClaimRepositroy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClaimsService {
    private final ClaimRepositroy claimsRepository;

    public ClaimsService(ClaimRepositroy claimsRepository) {
        this.claimsRepository = claimsRepository;
    }


    public Claim save(Claim claim) {
        return claimsRepository.save(claim);
    }

    public Claim findById(Integer id) {
        return claimsRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Cannot find policy with Id::" + id));
    }

    public List<Claim> getAllClaims() {
        return claimsRepository.findAll();
    }

    public Claim updateClaim(Claim claim, Integer id) {
        Claim existingClaim = claimsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Claim not found with Id::" + id));

        existingClaim.setAmount(claim.getAmount());
        existingClaim.setCreatedAt(claim.getCreatedAt());
        existingClaim.setDescription(claim.getDescription());
        existingClaim.setPolicy(claim.getPolicy());
        existingClaim.setUser(claim.getUser());
        existingClaim.setStatus(claim.getStatus());

        return claimsRepository.save(existingClaim);
    }

    public String deleteById(Integer id) {
        Claim existingClaim = claimsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Claim not found with Id::" + id));
        existingClaim.setStatus("DELETED");
        claimsRepository.save(existingClaim);
        return "Claim deleted successfully";
    }
}
