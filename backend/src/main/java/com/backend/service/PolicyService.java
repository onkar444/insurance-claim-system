package com.backend.service;

import com.backend.model.dto.PolicyRequestDTO;
import com.backend.model.dto.PolicyResponseDTO;
import com.backend.repository.PolicyRepository;
import com.backend.repository.exception.PolicyNotFoundException;
import com.backend.utility.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PolicyService {
    private final PolicyRepository policyRepository;

    @Autowired
    public PolicyService(PolicyRepository policyRepository) {
        this.policyRepository = policyRepository;
    }

    public List<PolicyResponseDTO> getAllPolicies() {
        return policyRepository.findAll().stream()
                .map(MapperUtils::mapPolicyEntityToDto)
                .toList();
    }

    public PolicyResponseDTO findById(Integer id) {
        var policy = policyRepository.findById(id)
                .orElseThrow(() -> new PolicyNotFoundException("Policy not found with id::" + id));

        return MapperUtils.mapPolicyEntityToDto(policy);
    }

    public PolicyResponseDTO updatePolicy(PolicyRequestDTO policy, Integer id) {
        var existingPolicy = policyRepository.findById(id)
                .orElseThrow(() -> new PolicyNotFoundException("Policy not found with id::" + id));

        existingPolicy.setCoverageAmount(policy.coverageAmount());
        existingPolicy.setPolicyType(policy.policyType());
        existingPolicy.setPremium(policy.premium());
        existingPolicy.setEndDate(policy.endDate());
        existingPolicy.setStartDate(policy.startDate());

        policyRepository.save(existingPolicy);

        return MapperUtils.mapPolicyEntityToDto(existingPolicy);
    }

    public PolicyResponseDTO savePolicy(PolicyRequestDTO policy) {
       var savedPolicy = policyRepository.save(MapperUtils.mapPolicyRequestDTOtoEntity(policy));

        return MapperUtils.mapPolicyEntityToDto(savedPolicy);
    }

    public String deleteById(Integer id) {
        policyRepository.deleteById(id);
        return "Policy deleted successfully";
    }

    public PolicyResponseDTO getById(Integer id) {
        var policy = policyRepository.findById(id)
                .orElseThrow(() -> new PolicyNotFoundException("Policy not found with id:" + id));


        return MapperUtils.mapPolicyEntityToDto(policy);
    }
}
