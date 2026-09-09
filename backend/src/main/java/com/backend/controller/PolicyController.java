package com.backend.controller;

import com.backend.model.Policy;
import com.backend.model.dto.ClaimResponseDTO;
import com.backend.model.dto.PolicyRequestDTO;
import com.backend.model.dto.PolicyResponseDTO;
import com.backend.service.PolicyService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PolicyController {

    private final PolicyService policyService;

    public PolicyController(PolicyService policyService) {
        this.policyService = policyService;
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/policies")
    public ResponseEntity<List<PolicyResponseDTO>> getAllPolicies() {
        return ResponseEntity.ok(policyService.getAllPolicies());
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PostMapping("/policy/save")
    public ResponseEntity<PolicyResponseDTO> savePolicy(@RequestBody PolicyRequestDTO policy) {
        return ResponseEntity.ok(policyService.savePolicy(policy));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/policy/update/{id}")
    public ResponseEntity<PolicyResponseDTO> updatePolicy(@RequestBody PolicyRequestDTO policy, @PathVariable Long id) {
        return ResponseEntity.ok(policyService.updatePolicy(policy, id));
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/policy/{id}")
    public ResponseEntity<PolicyResponseDTO> getPolicyById(@PathVariable Long id){
        return ResponseEntity.ok(policyService.getById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/policy/{id}")
    public ResponseEntity<String> deletePolicyByPolicyNumber(@PathVariable Long id){
        return ResponseEntity.ok(policyService.deleteById(id));
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/policy/{id}/claims")
    public ResponseEntity<List<ClaimResponseDTO>> getClaimsByPolicyId(@PathVariable Long id){
        return ResponseEntity.ok(policyService.getClaimsByPolicyId(id));
    }

}
