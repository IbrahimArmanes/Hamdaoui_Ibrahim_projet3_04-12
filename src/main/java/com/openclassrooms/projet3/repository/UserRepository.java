package com.openclassrooms.projet3.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.openclassrooms.projet3.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
}
