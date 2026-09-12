package com.backend.security;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableMethodSecurity
@Configuration
public class WebSecurityConfig {

    private final JwtFilter jwtFilter;

    public WebSecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {
        return http
                .cors(cors -> {
                })
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req ->
//                        req.anyRequest().permitAll())
                        req.requestMatchers("/api/auth/**").permitAll()
                                .requestMatchers("/swagger-ui.html"
                                        , "/swagger-ui/**"
                                        , "/v3/api-docs/**"
                                        , "/v3/api-docs/").permitAll()
                                .requestMatchers("/error").permitAll()
                                .anyRequest().authenticated())
                .exceptionHandling(exception -> {
                    exception
                            .authenticationEntryPoint(((request,
                                                        response,
                                                        authException) -> {
                                System.out.println(
                                        ">>> AUTHENTICATION ENTRY POINT: " +
                                                authException.getMessage()
                                );
                                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
                            }))
                            .accessDeniedHandler(((request,
                                                   response,
                                                   accessDeniedException) -> {
                                System.out.println(
                                        ">>> ACCESS DENIED: " +
                                                accessDeniedException.getMessage()
                                );

                                response.sendError(HttpServletResponse.SC_FORBIDDEN, "Forbidden");
                            }));
                })
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) {
        return authConfig.getAuthenticationManager();
    }

}
