package com.backend.service;

import com.backend.model.Claim;
import com.backend.model.dto.ClaimResponseDTO;
import com.backend.model.dto.PolicyRequestDTO;
import com.backend.model.dto.PolicyResponseDTO;
import com.backend.repository.ClaimRepositroy;
import com.backend.repository.PolicyRepository;
import com.backend.repository.UserRepository;
import com.backend.repository.exception.PolicyNotFoundException;
import com.backend.repository.exception.UserNotFoundException;
import com.backend.utility.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PolicyService {
    private final PolicyRepository policyRepository;
    private final ClaimRepositroy claimRepositroy;
    private final UserRepository userRepository;

    @Autowired
    public PolicyService(PolicyRepository policyRepository, ClaimRepositroy claimRepositroy, UserRepository userRepository) {
        this.policyRepository = policyRepository;
        this.claimRepositroy = claimRepositroy;
        this.userRepository = userRepository;
    }

    public List<PolicyResponseDTO> getAllPolicies() {
        return policyRepository.findAll().stream()
                .map(MapperUtils::mapPolicyEntityToDto)
                .toList();
    }

    public PolicyResponseDTO updatePolicy(PolicyRequestDTO policy, Integer id) {

        var userEntity = userRepository.findById(policy.userId())
                .orElseThrow(()->new UserNotFoundException("User not found with Id::"+policy.userId()));

        var existingPolicy = policyRepository.findById(id)
                .orElseThrow(() -> new PolicyNotFoundException("Policy not found with id::" + id));

        existingPolicy.setCoverageAmount(policy.coverageAmount());
        existingPolicy.setPolicyType(policy.policyType());
        existingPolicy.setPremium(policy.premium());
        existingPolicy.setEndDate(policy.endDate());
        existingPolicy.setStartDate(policy.startDate());
        existingPolicy.setStatus(policy.status());
        existingPolicy.setUser(userEntity);

         var updatedPolicy = policyRepository.save(existingPolicy);

        return MapperUtils.mapPolicyEntityToDto(updatedPolicy);
    }

    public PolicyResponseDTO savePolicy(PolicyRequestDTO policy) {
        var userEntity = userRepository.findById(policy.userId())
                .orElseThrow(()->new UserNotFoundException("User not found with Id::"+policy.userId()));

        var policyEntity = MapperUtils.mapPolicyRequestDTOtoEntity(policy);
        policyEntity.setUser(userEntity);
        var savedPolicy = policyRepository.save(policyEntity);

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

    public List<ClaimResponseDTO> getClaimsByPolicyId(Integer id) {
        return claimRepositroy.findAllByPolicyId(id)
                .stream()
                .map(MapperUtils::mapClaimEntityToResponseDTO)
                .toList();
    }
}
