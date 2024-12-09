package com.openclassrooms.projet3.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class MessageRequest {
    @JsonProperty("userId")
    private Long userId;
    
    @JsonProperty("rentalId")
    private Long rentalId;
    
    private String message;
}
