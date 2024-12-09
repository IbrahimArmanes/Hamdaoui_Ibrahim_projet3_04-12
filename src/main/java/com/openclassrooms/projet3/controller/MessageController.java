package com.openclassrooms.projet3.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.projet3.dto.MessageRequest;
import com.openclassrooms.projet3.model.Message;
import com.openclassrooms.projet3.model.User;
import com.openclassrooms.projet3.service.MessageService;
import com.openclassrooms.projet3.service.RentalService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;
    private final RentalService rentalService;
    
    @PostMapping("/messages")
    public ResponseEntity<Message> createMessage(
        @RequestBody MessageRequest messageRequest, 
        @AuthenticationPrincipal User user) {
        
        Message message = Message.builder()
            .message(messageRequest.getMessage())
            .rental(rentalService.getRentalById(messageRequest.getRentalId()))
            .user(user)
            .build();
            
        Message savedMessage = messageService.saveMessage(message);
        return ResponseEntity.ok(savedMessage);
    }
}