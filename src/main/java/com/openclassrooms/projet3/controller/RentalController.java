package com.openclassrooms.projet3.controller;

import com.openclassrooms.projet3.dto.MessageResponse;
import com.openclassrooms.projet3.dto.RentalResponse;
import com.openclassrooms.projet3.dto.RentalUpdateRequest;
import com.openclassrooms.projet3.dto.RentalUpdateResponse;
import com.openclassrooms.projet3.model.Rental;
import com.openclassrooms.projet3.service.RentalService;
import lombok.RequiredArgsConstructor;
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
import org.springframework.http.HttpStatus;
import com.openclassrooms.projet3.service.JwtService;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RentalController {
    private final RentalService rentalService;
    private final JwtService jwtService;

    @GetMapping("/rentals")
    public ResponseEntity<RentalResponse> getRentals() {
        return ResponseEntity.ok(rentalService.getAllRentals());
    }
    @GetMapping("/rentals/{id}")
    public ResponseEntity<Rental> getRentalById(@PathVariable Long id) {
        try {
            Rental rental = rentalService.getRentalById(id);
            return ResponseEntity.ok(rental);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping(value = "/rentals/{id}")
    public ResponseEntity<RentalUpdateResponse> updateRental(
        @PathVariable Long id,
        @ModelAttribute RentalUpdateRequest request
    ) {
        try {
            RentalUpdateResponse response = rentalService.updateRental(id, request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/rentals")
    public ResponseEntity<?> createRental(
            @RequestHeader("Authorization") String token,
            @RequestParam("name") String name,
            @RequestParam("surface") Double surface,
            @RequestParam("price") Double price,
            @RequestParam("description") String description,
            @RequestParam("picture") MultipartFile picture) {
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


