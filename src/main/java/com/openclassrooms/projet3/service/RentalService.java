package com.openclassrooms.projet3.service;



import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.openclassrooms.projet3.dto.RentalResponse;
import com.openclassrooms.projet3.dto.RentalUpdateRequest;
import com.openclassrooms.projet3.dto.RentalUpdateResponse;
import com.openclassrooms.projet3.interfaces.IRentalService;
import com.openclassrooms.projet3.model.Rental;
import com.openclassrooms.projet3.repository.RentalRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RentalService implements IRentalService {
    private final RentalRepository rentalRepository;
    private final FileService fileService;
    @Override
    public Rental createRental(String name, Double surface, Double price, String description, MultipartFile picture, Integer userId) 
    throws Exception {
        String picturePath = fileService.saveFile(picture);
        
        return rentalRepository.save(Rental.builder()
            .name(name)
            .surface(surface)
            .price(price)
            .description(description)
            .picture(picturePath)
            .owner_id(userId)
            .build());
    }
    @Override
    public RentalResponse getAllRentals() {
        RentalResponse response = new RentalResponse("Affichage ok");
        response.setRentals(rentalRepository.findAll());
        return response;
    }
    @Override
    public Rental getRentalById(Long id) {
        return rentalRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Rental not found"));
    }
    @Override
    public RentalUpdateResponse updateRental(Long id, RentalUpdateRequest request) {
        Rental rental = rentalRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Rental not found"));
        
        try {
            if (request.getName() != null) rental.setName(request.getName());
            if (request.getSurface() != null) rental.setSurface(request.getSurface());
            if (request.getPrice() != null) rental.setPrice(request.getPrice());
            if (request.getPicture() != null) {
                String picturePath = fileService.saveFile(request.getPicture());
                rental.setPicture(picturePath);
            }
            if (request.getDescription() != null) rental.setDescription(request.getDescription());
            
            rentalRepository.save(rental);
            
            RentalUpdateResponse response = new RentalUpdateResponse();
            response.setMessage("Rental updated !");
            return response;
        } catch (Exception e) {
            throw new RuntimeException("Error updating rental: " + e.getMessage());
        }
    }

}
