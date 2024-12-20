package com.openclassrooms.projet3.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class MessageRequest {
    @JsonProperty("user_id")
    private Long userId;
    
    @JsonProperty("rental_id")
    private Long rentalId;
    
    private String message;
}
