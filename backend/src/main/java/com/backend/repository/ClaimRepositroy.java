package com.backend.repository;

import com.backend.model.Claim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClaimRepositroy extends JpaRepository<Claim, Integer> {
    List<Claim> findAllByPolicyId(Integer id);
}
