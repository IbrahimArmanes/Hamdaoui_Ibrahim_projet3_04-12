package com.openclassrooms.projet3.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.projet3.model.RegisterRequest;
import com.openclassrooms.projet3.model.RegisterResponse;
import com.openclassrooms.projet3.service.RegisterService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class RegisterController {
    
    private final RegisterService authService;
    //Modifier pour retourner error 400 quand la registration échoue
    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request) {
        if (request.getName() == null || request.getEmail() == null || request.getPassword() == null) {
            return ResponseEntity.accepted().body(new RegisterResponse());
        }
        
        RegisterResponse response = authService.register(request);
        return ResponseEntity.ok(response);
    }
}
