package com.backend.controller;

import com.backend.model.User;
import com.backend.model.dto.UserRequestDTO;
import com.backend.security.JwtUtil;
import com.backend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthController {

    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;


    public AuthController(JwtUtil jwtUtil, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, UserService userService) {
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @PostMapping("/auth/register")
    public ResponseEntity<?> registerUser (@RequestBody UserRequestDTO userRequestDTO){
        User user = User.builder()
                .email(userRequestDTO.email())
                .name(userRequestDTO.name())
                .password(passwordEncoder.encode(userRequestDTO.password()))
                .role("USER")
                .build();

        userService.registerUser(user);
        return ResponseEntity.ok("User Registered successfully");
    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> loginUser (@RequestBody UserRequestDTO userRequestDTO){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userRequestDTO.email(), userRequestDTO.password())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtUtil.generateToken(userRequestDTO.email());
        return ResponseEntity.ok("Token :: "+token);
    }
}
