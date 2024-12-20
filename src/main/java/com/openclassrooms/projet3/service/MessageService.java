package com.openclassrooms.projet3.service;

import org.springframework.stereotype.Service;

import com.openclassrooms.projet3.interfaces.IMessageService;
import com.openclassrooms.projet3.model.Message;
import com.openclassrooms.projet3.repository.MessageRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MessageService implements IMessageService {
    private final MessageRepository messageRepository;

    // Saves new message to database
    @Override
    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }
}
