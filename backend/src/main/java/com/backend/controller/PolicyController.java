package com.backend.controller;

import com.backend.model.Policy;
import com.backend.model.dto.PolicyRequestDTO;
import com.backend.model.dto.PolicyResponseDTO;
import com.backend.service.PolicyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PolicyController {

    private final PolicyService policyService;

    public PolicyController(PolicyService policyService) {
        this.policyService = policyService;
    }

    @GetMapping("/policies")
    public ResponseEntity<List<PolicyResponseDTO>> getAllPolicies() {
        return ResponseEntity.ok(policyService.getAllPolicies());
    }

    @PostMapping("/policy/save")
    public ResponseEntity<PolicyResponseDTO> savePolicy(@RequestBody PolicyRequestDTO policy) {
        return ResponseEntity.ok(policyService.savePolicy(policy));
    }

    @PostMapping("/policy/update/{id}")
    public ResponseEntity<PolicyResponseDTO> updatePolicy(@RequestBody PolicyRequestDTO policy, @PathVariable Integer id) {
        return ResponseEntity.ok(policyService.updatePolicy(policy, id));
    }

    @GetMapping("/policy/{id}")
    public ResponseEntity<PolicyResponseDTO> getPolicyById(@PathVariable Integer id){
        return ResponseEntity.ok(policyService.getById(id));
    }

    @DeleteMapping("/policy/{id}")
    public ResponseEntity<String> deletePolicyByPolicyNumber(@PathVariable Integer id){
        return ResponseEntity.ok(policyService.deleteById(id));
    }
}
