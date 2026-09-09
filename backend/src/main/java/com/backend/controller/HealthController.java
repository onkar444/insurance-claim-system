package com.backend.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/")
public class HealthController {

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/health")
    public String getMethodName() {
        return "Backend is UP and running";
    }

}
