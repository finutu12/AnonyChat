package org.example.demo.service;

import org.example.demo.model.ChatSession;
import org.example.demo.repossitory.ChatSessionRepossitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatSessionService {
    private final ChatSessionRepossitory chatSessionRepossitory;

    @Autowired
    public ChatSessionService(ChatSessionRepossitory chatSessionRepossitory) {
        this.chatSessionRepossitory = chatSessionRepossitory;
    }

    public ChatSession saveChatSession(ChatSession chatSession) {
        return chatSessionRepossitory.save(chatSession);
    }

    public ChatSession getChatSessionById(String id) {
        return chatSessionRepossitory.findById(id).orElse(null);
    }

    // Other methods as needed
}
