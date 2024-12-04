package com.openclassrooms.projet3.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.openclassrooms.projet3.model.RegisterRequest;
import com.openclassrooms.projet3.model.RegisterResponse;
import com.openclassrooms.projet3.model.User;
import com.openclassrooms.projet3.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegisterService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    
    public RegisterResponse register(RegisterRequest request) {
        if (request.getName() == null || request.getEmail() == null || request.getPassword() == null) {
            return new RegisterResponse();
        }
        
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already taken");
        }
        
        var user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
                
        userRepository.save(user);
        
        String token = jwtService.generateToken(user);
        return new RegisterResponse(token);
    }
}
