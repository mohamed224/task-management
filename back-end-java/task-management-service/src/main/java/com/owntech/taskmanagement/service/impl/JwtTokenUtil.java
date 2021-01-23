package com.owntech.taskmanagement.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@Slf4j
public class JwtTokenUtil {

    private static final String SECRET_KEY = "jwtSecretKey";
    private static final long EXPIRATION_DATE = 8640000;
    private static final String AUTHORITIES = "authorities";
    private static final String SUBJECT = "sub";
    private static final String EXPIRATION = "exp";
    private static final String ISSUED_AT = "iat";


    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + EXPIRATION_DATE);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        List<String> authorities = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        claims.put(AUTHORITIES, authorities);
        claims.put(SUBJECT,userDetails.getUsername());
        claims.put(EXPIRATION,generateExpirationDate());
        claims.put(ISSUED_AT,new Date(System.currentTimeMillis()));
        log.info("username: {} ", userDetails.getUsername());
        return createToken(claims);
    }

    private String createToken(Map<String, Object> claims) {
        return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        return !isTokenExpired(token) && userDetails.getUsername().equals(extractUsername(token));
    }

}
