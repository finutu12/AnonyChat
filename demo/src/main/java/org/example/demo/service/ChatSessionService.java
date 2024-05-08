package org.example.demo.service;

import org.example.demo.model.ChatSession;
import org.example.demo.repossitory.ChatSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatSessionService {
    private final ChatSessionRepository chatSessionRepository;

    @Autowired
    public ChatSessionService(ChatSessionRepository chatSessionRepository) {
        this.chatSessionRepository = chatSessionRepository;
    }

    public ChatSession saveChatSession(ChatSession chatSession) {
        return chatSessionRepository.save(chatSession);
    }

    public ChatSession getChatSessionById(Integer id) {
        return chatSessionRepository.findById(id).orElse(null);
    }

    // Other methods as needed
}
