package com.openclassrooms.projet3.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class RentalUpdateRequest {
    private String name;
    private Double surface;
    private Double price;
    private MultipartFile picture;
    private String description;
}
