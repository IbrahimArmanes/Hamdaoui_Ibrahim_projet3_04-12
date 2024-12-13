package com.openclassrooms.projet3.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.projet3.dto.UserDTO;
import com.openclassrooms.projet3.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@Tag(name = "Users", description = "APIs for user management")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(summary = "Get current user", description = "Retrieves the currently authenticated user's information")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "User information retrieved successfully"),
        @ApiResponse(responseCode = "401", description = "Not authenticated")
    })
    @GetMapping("/auth/me")
    public ResponseEntity<UserDTO> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDTO userDTO = userService.getUserByEmail(authentication.getName());
        return ResponseEntity.ok(userDTO);
    }

    @Operation(summary = "Get user by ID", description = "Retrieves a specific user by their ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "User found"),
        @ApiResponse(responseCode = "404", description = "User not found")
    })
    @GetMapping("/user/{id}")
    public ResponseEntity<UserDTO> getUserById(
        @Parameter(description = "User ID", example = "1") 
        @PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }
}
