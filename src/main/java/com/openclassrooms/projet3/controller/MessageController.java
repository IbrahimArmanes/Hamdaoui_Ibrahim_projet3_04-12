package com.openclassrooms.projet3.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.projet3.dto.MessageRequest;
import com.openclassrooms.projet3.interfaces.IMessageService;
import com.openclassrooms.projet3.interfaces.IRentalService;
import com.openclassrooms.projet3.model.Message;
import com.openclassrooms.projet3.model.User;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
@RestController
@RequestMapping("/api")
@Tag(name = "Messages", description = "APIs for managing rental messages")
@RequiredArgsConstructor
public class MessageController {
    private final IMessageService messageService;
    private final IRentalService rentalService;
    
    @Operation(summary = "Create new message", description = "Creates a new message for a rental")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Message created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid message data")
    })
    @PostMapping("/messages")
    public ResponseEntity<Message> createMessage(
        @RequestBody @Valid MessageRequest messageRequest, 
        @Parameter(hidden = true) @AuthenticationPrincipal User user) {
        
        Message message = Message.builder()
            .message(messageRequest.getMessage())
            .rental(rentalService.getRentalById(messageRequest.getRentalId()))
            .user(user)
            .build();
            
        Message savedMessage = messageService.saveMessage(message);
        return ResponseEntity.ok(savedMessage);
    }
}