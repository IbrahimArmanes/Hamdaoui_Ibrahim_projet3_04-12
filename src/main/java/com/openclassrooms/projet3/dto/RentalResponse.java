package com.openclassrooms.projet3.dto;

import lombok.Data;
import java.util.List;

import com.openclassrooms.projet3.model.Rental;

@Data
public class RentalResponse {
    private List<Rental> rentals;
    private String message;

    public RentalResponse(String message) {
        this.message = message;
    }

}
