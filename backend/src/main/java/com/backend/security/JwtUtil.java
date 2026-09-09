package com.backend.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.List;

@Component
public class JwtUtil {
    private static final String SECRET_KEY = "my-secret-key-onkar-rautmy-secret-key-onkar-rautmy-secret-key-onkar-raut";
    private static final long EXPIRATION_MS = 15 * 60 * 1000;
    private final CustomUserDetailsService userDetailsService;

    public JwtUtil(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public static Key getSigningKey(){
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken (String username){

        UserDetails user = userDetailsService.loadUserByUsername(username);
        List<String> roles = user.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        return Jwts.builder()
                .setSubject(username)
                .claim("roles",roles)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_MS))
                .signWith(getSigningKey(),SignatureAlgorithm.HS256)
                .compact();
    }

    public String getUserName(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();

    }

    public boolean isExpiredToken(String token){
        Date expiration = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();

        return expiration.before(new Date());
    }

    public boolean validateToken(String token, UserDetails userDetails){
        final String username = getUserName(token);
        return (username.equals(userDetails.getUsername()) && ! isExpiredToken(token));
    }

    public List<SimpleGrantedAuthority> extractClaims(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        List<String> authorities = claims.get("roles",List.class);

        return authorities.stream()
                .map(SimpleGrantedAuthority::new)
                .toList();
    }
}
