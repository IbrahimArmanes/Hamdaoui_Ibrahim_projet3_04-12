package com.openclassrooms.projet3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.projet3.model.Rental;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {
}
