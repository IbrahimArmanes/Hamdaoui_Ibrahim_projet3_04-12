package com.openclassrooms.projet3.service;

import com.openclassrooms.projet3.model.Message;
import com.openclassrooms.projet3.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;
    
    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }
}
