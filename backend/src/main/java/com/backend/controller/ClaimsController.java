package com.backend.controller;

import com.backend.model.Claim;
import com.backend.service.ClaimsService;
import org.springframework.http.ResponseEntity;
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
    @PostMapping("/claim/save")
    public ResponseEntity<Claim> saveClaim(@RequestBody Claim claim) {
        return ResponseEntity.ok(claimsService.save(claim));
    }

    //    Get claim
    @GetMapping("/claim/{id}")
    public ResponseEntity<Claim> getClaimById(@PathVariable Integer id) {
        return ResponseEntity.ok(claimsService.findById(id));
    }

    //    Get all claims
    @GetMapping("/claim/all")
    public ResponseEntity<List<Claim>> getAllClaims() {
        return ResponseEntity.ok(claimsService.getAllClaims());
    }

    //    Update claim
    @PostMapping("/claim/update/{id}")
    public ResponseEntity<Claim> updateClaim(@RequestBody Claim claim, @PathVariable Integer id) {
        return ResponseEntity.ok(claimsService.updateClaim(claim,id));
    }

    //    Delete/deactivate claim
    @DeleteMapping("/claim/delete/{id}")
    public ResponseEntity<String> deleteClaimById(@PathVariable Integer id){
        return ResponseEntity.ok(claimsService.deleteById(id));
    }
}
