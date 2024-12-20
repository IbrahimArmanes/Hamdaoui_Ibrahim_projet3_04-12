package com.openclassrooms.projet3.service;

import org.springframework.stereotype.Service;

import com.openclassrooms.projet3.dto.UserDTO;
import com.openclassrooms.projet3.interfaces.IUserService;
import com.openclassrooms.projet3.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;
      // Retrieves user information by email and converts to DTO
      @Override
      public UserDTO getUserByEmail(String email) {
          return userRepository.findByEmail(email)
              .map(user -> UserDTO.builder()
                  .id(user.getId())
                  .name(user.getName())
                  .email(user.getEmail())
                  .build())
              .orElseThrow(() -> new RuntimeException("User not found"));
      }
    
      // Retrieves user information by ID and converts to DTO
      @Override
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
