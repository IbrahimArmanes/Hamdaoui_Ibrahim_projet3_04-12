package com.openclassrooms.projet3.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.openclassrooms.projet3.config.JwtProperties;
import com.openclassrooms.projet3.interfaces.IJwtService;
import com.openclassrooms.projet3.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService implements IJwtService {

    private final JwtProperties jwtProperties;

    public JwtService(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }
      // Generates JWT token for authenticated user
      @Override
      public String generateToken(User user) {
          Map<String, Object> claims = new HashMap<>();
          claims.put("email", user.getEmail());
          claims.put("name", user.getName());
          claims.put("id", user.getId());
          return createToken(claims, user.getEmail());
      }

      // Creates JWT token with claims
      private String createToken(Map<String, Object> claims, String subject) {
          return Jwts.builder()
                  .setClaims(claims)
                  .setSubject(subject)
                  .setIssuer(jwtProperties.getIssuer())
                  .setAudience(jwtProperties.getAudience())
                  .setIssuedAt(new Date(System.currentTimeMillis()))
                  .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getExpiration()))
                  .signWith(Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes()), 
                         SignatureAlgorithm.HS256)
                  .compact();
      }

      // Validates JWT token
      @Override
      public boolean validateToken(String token) {
          try {
              Jwts.parserBuilder()
                  .setSigningKey(Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes()))
                  .build()
                  .parseClaimsJws(token);
              return true;
          } catch (Exception e) {
              return false;
          }
      }

      // Extracts email from JWT token
      @Override
      public String extractEmail(String token) {
          return extractClaim(token, Claims::getSubject);
      }

      // Extracts specific claim from token
      public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
          final Claims claims = extractAllClaims(token);
          return claimsResolver.apply(claims);
      }

      // Extracts all claims from token
      private Claims extractAllClaims(String token) {
          return Jwts.parserBuilder()
                  .setSigningKey(Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes()))
                  .build()
                  .parseClaimsJws(token)
                  .getBody();
      }

      // Extracts user ID from token
      @Override
      public Integer extractUserId(String token) {
          return extractClaim(token, claims -> claims.get("id", Integer.class));
    }
}


