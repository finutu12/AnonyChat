package org.example.demo.service;

import org.example.demo.model.ChatSession;
import org.example.demo.repossitory.ChatSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<ChatSession> getFreeChatSession() {
        return chatSessionRepository.findChatSessionsWithSingleUser();
    }

    public void deleteAll(List<ChatSession> chatSessionList) {
        this.chatSessionRepository.deleteAll();
    }

    public List<ChatSession> getEmptyChatSession() {
        return chatSessionRepository.findEmptySessions();
    }

    public ChatSession save(ChatSession chatSession) {
        return chatSessionRepository.save(chatSession);
    }

    public ChatSession getChatSessionById(Integer id) {
        return chatSessionRepository.findById(id).orElse(null);
    }

    public ChatSession findById(Integer id) {
        return this.chatSessionRepository.findById(id).orElse(null);
    }

    public void delete(ChatSession chatSession) {
        chatSessionRepository.delete(chatSession);
    }

    public ChatSession findByUser(Integer id) {
        return this.chatSessionRepository.findByUsersIdIn(List.of(id));
    }

    // Other methods as needed
}
