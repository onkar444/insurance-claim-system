package com.backend.controller;

import com.backend.model.dto.ClaimRequestDTO;
import com.backend.model.dto.ClaimResponseDTO;
import com.backend.service.ClaimsService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ClaimsController {
    private final ClaimsService claimsService;

    public ClaimsController(ClaimsService claimsService) {
        this.claimsService = claimsService;
    }

    //    Create claim
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PostMapping("/claim/save")
    public ResponseEntity<ClaimResponseDTO> saveClaim(@RequestBody ClaimRequestDTO claim) {
        return ResponseEntity.ok(claimsService.save(claim));
    }

    //    Get claim
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/claim/{id}")
    public ResponseEntity<ClaimResponseDTO> getClaimById(@PathVariable Long id) {
        return ResponseEntity.ok(claimsService.findById(id));
    }

    //    Get all claims
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/claim/all")
    public ResponseEntity<List<ClaimResponseDTO>> getAllClaims() {
        return ResponseEntity.ok(claimsService.getAllClaims());
    }

    //    Update claim
    @PostMapping("/claim/update/{id}")
    public ResponseEntity<ClaimResponseDTO> updateClaim(@RequestBody ClaimRequestDTO claim,
                                                        @PathVariable Long id) {
        return ResponseEntity.ok(claimsService.updateClaim(claim, id));
    }

    //    Delete/deactivate claim
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/claim/delete/{id}")
    public ResponseEntity<String> deleteClaimById(@PathVariable Long id) {
        return ResponseEntity.ok(claimsService.deleteById(id));
    }

    // Get assigned Claims
    @GetMapping("/claim/assigned")
    public ResponseEntity<List<ClaimResponseDTO>> getAllAssignedClaims(Authentication authentication) {
        return ResponseEntity.ok(claimsService.getAllAssignedClaims(authentication));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/claim/{claimId}/assign/{adjusterId}")
    public ResponseEntity<ClaimResponseDTO> assignClaim(@PathVariable Long adjusterId, @PathVariable Long claimId) {
        return ResponseEntity.ok(claimsService.assignClaim(adjusterId, claimId));
    }

}
