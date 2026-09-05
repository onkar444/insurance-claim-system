package com.backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/")
public class HealthController {

    @GetMapping("/health")
    public String getMethodName() {
        return "Backend is UP and running";
    }

}
