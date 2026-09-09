package com.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Claim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Double amount;
    @Enumerated(EnumType.STRING)
    private CLAIM_STATUS status;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    // Many claims can have one user
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="customer_id",nullable = false)
    private User customer;

    // Many claims can be assigned to one adjuster
    @ManyToOne
    @JoinColumn(name="adjuster_id", nullable = true)
    private User adjuster;

    // Many claims can have one policy
    @ManyToOne
    @JoinColumn(name="policy_id", nullable = false)
    private Policy policy;

    private boolean deleted;

}
