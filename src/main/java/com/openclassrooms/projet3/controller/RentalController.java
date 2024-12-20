package com.openclassrooms.projet3.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.openclassrooms.projet3.dto.MessageResponse;
import com.openclassrooms.projet3.dto.RentalResponse;
import com.openclassrooms.projet3.dto.RentalUpdateRequest;
import com.openclassrooms.projet3.dto.RentalUpdateResponse;
import com.openclassrooms.projet3.interfaces.IJwtService;
import com.openclassrooms.projet3.interfaces.IRentalService;
import com.openclassrooms.projet3.model.Rental;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@Tag(name = "Rentals", description = "APIs for managing rental properties")
@RequiredArgsConstructor
public class RentalController {
    private final IRentalService rentalService;
    private final IJwtService jwtService;

    @Operation(summary = "Get all rentals", description = "Retrieves a list of all available rentals")
    @GetMapping("/rentals")
    public ResponseEntity<RentalResponse> getRentals() {
        return ResponseEntity.ok(rentalService.getAllRentals());
    }

    @Operation(summary = "Get rental by ID", description = "Retrieves a specific rental by its ID")
    @GetMapping("/rentals/{id}")
    public ResponseEntity<Rental> getRentalById(
        @Parameter(description = "Rental ID", example = "1") 
        @PathVariable Long id) {
        try {
            Rental rental = rentalService.getRentalById(id);
            return ResponseEntity.ok(rental);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Update rental", description = "Updates an existing rental property")
    @PutMapping(value = "/rentals/{id}")
    public ResponseEntity<RentalUpdateResponse> updateRental(
        @Parameter(description = "Rental ID", example = "1") 
        @PathVariable Long id,
        @ModelAttribute RentalUpdateRequest request) {
        try {
            RentalUpdateResponse response = rentalService.updateRental(id, request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Create new rental", description = "Creates a new rental property")
    @PostMapping("/rentals")
    public ResponseEntity<?> createRental(
        @Parameter(hidden = true) @RequestHeader("Authorization") String token,
        @Parameter(description = "Rental name") @RequestParam("name") String name,
        @Parameter(description = "Surface area in m²") @RequestParam("surface") Double surface,
        @Parameter(description = "Price per night") @RequestParam("price") Double price,
        @Parameter(description = "Rental description") @RequestParam("description") String description,
        @Parameter(description = "Property image") @RequestParam("picture") MultipartFile picture) {
        try {
            String jwt = token.replace("Bearer ", "");
            Integer userId = jwtService.extractUserId(jwt);

            rentalService.createRental(name, surface, price, description, picture, userId);
            return ResponseEntity.ok(new MessageResponse("Rental created !"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new MessageResponse("Error creating rental"));
        }
    }
}


