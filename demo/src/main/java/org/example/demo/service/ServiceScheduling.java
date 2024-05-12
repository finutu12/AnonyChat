package org.example.demo.service;

import org.example.demo.model.ChatSession;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ServiceScheduling {
    private final ChatSessionService chatSessionService;

    ServiceScheduling(final ChatSessionService chatSessionService) {
        this.chatSessionService = chatSessionService;
    }

    @Scheduled(fixedRate = 3600000) // 10 minutes = 600000 milliseconds
    public void myScheduledTask() {
        List<ChatSession> chatSessionList = this.chatSessionService.getEmptyChatSession();
        this.chatSessionService.deleteAll(chatSessionList);
    }
}