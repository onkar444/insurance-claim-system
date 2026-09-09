package com.backend.repository;

import com.backend.model.Claim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClaimRepositroy extends JpaRepository<Claim, Long> {
    List<Claim> findAllByPolicyId(Long id);

    Optional<Claim> findByAdjusterId(Long currentUserId);
}
