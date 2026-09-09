package com.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JoinColumnOrFormula;

import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private List<ROLE> roles;

    // One User can have multiple policies
    @OneToMany(mappedBy = "customer")
    private List<Policy> policies;

    //Claims where this user is the customer
    @OneToMany(mappedBy = "customer")
    private List<Claim> customerClaims;

    //Claims where this user is the adjuster
    @OneToMany(mappedBy = "adjuster")
    private List<Claim> assignedClaims;

    private boolean deleted;
}
