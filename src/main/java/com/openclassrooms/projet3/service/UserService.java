package com.openclassrooms.projet3.service;

import org.springframework.stereotype.Service;

import com.openclassrooms.projet3.dto.UserDTO;
import com.openclassrooms.projet3.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserDTO getUserByEmail(String email) {
        return userRepository.findByEmail(email)
            .map(user -> UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build())
            .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public UserDTO getUserById(Long id) {
        return userRepository.findById(id)
            .map(user -> UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .created_at(user.getCreated_at())
                .updated_at(user.getUpdated_at())
                .build())
            .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

}
