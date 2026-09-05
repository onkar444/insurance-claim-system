package com.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Claim {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String description;
    private Double amount;
    private String status;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    // Many claims can have one user
    @ManyToOne
    private User user;

    // Many claims can have one policy
    @ManyToOne
    private Policy policy;

}
