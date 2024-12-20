package com.openclassrooms.projet3.interfaces;

import org.springframework.web.multipart.MultipartFile;
import com.openclassrooms.projet3.dto.RentalResponse;
import com.openclassrooms.projet3.dto.RentalUpdateRequest;
import com.openclassrooms.projet3.dto.RentalUpdateResponse;
import com.openclassrooms.projet3.model.Rental;

public interface IRentalService {
    Rental createRental(String name, Double surface, Double price, String description, MultipartFile picture, Integer userId) throws Exception;
    RentalResponse getAllRentals();
    Rental getRentalById(Long id);
    RentalUpdateResponse updateRental(Long id, RentalUpdateRequest request);
}
