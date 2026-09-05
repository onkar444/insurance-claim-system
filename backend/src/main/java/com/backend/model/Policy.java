package com.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor(force = true)
public class Policy {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String policyType;
    private Double premium;
    private Double coverageAmount;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;

    // One Policy can have multiple claims
    @OneToMany(mappedBy = "policy")
    private List<Claim> claims;

    // Many policies can have one user
    @ManyToOne
    private User user;

}
