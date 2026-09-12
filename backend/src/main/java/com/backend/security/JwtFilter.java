package com.backend.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jspecify.annotations.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService customUserDetailsService;

    public JwtFilter(JwtUtil jwtUtil, CustomUserDetailsService customUserDetailsService) {
        this.jwtUtil = jwtUtil;
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");

        if (Objects.nonNull(authHeader) && authHeader.startsWith("Bearer ")
                && SecurityContextHolder.getContext().getAuthentication() == null) {

            String token = authHeader.substring(7);

            try {
                String username = jwtUtil.getUserName(token);
                UserDetails user = customUserDetailsService.loadUserByUsername(username);
                System.out.println("USER ROLE/AUTHORITIES: "+user.getAuthorities());
                if (jwtUtil.validateToken(token, user)) {
//                    List<SimpleGrantedAuthority> authorities = jwtUtil.extractClaims(token);
                    System.out.println("JWT USERNAME: " + username);
                    System.out.println("JWT AUTHORITIES: " + user.getAuthorities());

                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                            user,
                            null,
                            user.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(auth);
                    System.out.println(
                            "AUTHENTICATED: " +
                                    SecurityContextHolder.getContext()
                                            .getAuthentication()
                                            .isAuthenticated()
                    );

                }
            } catch (Exception e) {
                System.out.println("JWT authentication failed");
                System.out.println("Reason: " + e.getMessage());
                SecurityContextHolder.clearContext();
            }
        }
        filterChain.doFilter(request, response);
    }
}
