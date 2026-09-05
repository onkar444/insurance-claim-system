package com.backend.repository;

import com.backend.model.Claim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClaimRepositroy extends JpaRepository<Claim, Integer> {
}
