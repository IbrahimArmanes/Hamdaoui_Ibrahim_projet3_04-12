package com.openclassrooms.projet3.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import com.openclassrooms.projet3.model.LoginRequest;
import com.openclassrooms.projet3.model.LoginResponse;
import com.openclassrooms.projet3.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginService {
    
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public LoginResponse login(LoginRequest request) {
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    request.getEmail(),
                    request.getPassword()
                )
            );
            
            var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
            
            var token = jwtService.generateToken(user);
            return new LoginResponse(token);
        } catch (AuthenticationException e) {
            throw new RuntimeException("Invalid email/password");
        }
    }
}