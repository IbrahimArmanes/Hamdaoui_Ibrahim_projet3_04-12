package com.openclassrooms.projet3.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.projet3.interfaces.ILoginService;
import com.openclassrooms.projet3.interfaces.IRegisterService;
import com.openclassrooms.projet3.model.LoginRequest;
import com.openclassrooms.projet3.model.LoginResponse;
import com.openclassrooms.projet3.model.RegisterRequest;
import com.openclassrooms.projet3.model.RegisterResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication", description = "APIs for user registration and authentication")
@RequiredArgsConstructor
public class RegisterController {
    private final ILoginService loginService;
    private final IRegisterService authService;

    @Operation(summary = "Register new user", description = "Creates a new user account")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Registration successful"),
        @ApiResponse(responseCode = "400", description = "Invalid registration data")
    })
    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request) {
        if (request.getName() == null || request.getEmail() == null || request.getPassword() == null) {
            return ResponseEntity.accepted().body(new RegisterResponse());
        }
        
        RegisterResponse response = authService.register(request);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "User login", description = "Authenticates a user and returns a token")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Login successful"),
        @ApiResponse(responseCode = "401", description = "Invalid credentials")
    })
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            LoginResponse response = loginService.login(request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("message", "error"));
        }
    }
}





