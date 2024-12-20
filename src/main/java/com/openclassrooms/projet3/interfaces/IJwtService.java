package com.openclassrooms.projet3.interfaces;

import com.openclassrooms.projet3.model.User;

public interface IJwtService {
    String generateToken(User user);
    boolean validateToken(String token);
    String extractEmail(String token);
    Integer extractUserId(String token);
}
