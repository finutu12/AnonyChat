package org.example.demo.service;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class StompMessageService {

    private final SimpMessagingTemplate simpMessagingTemplate;

    public StompMessageService(final SimpMessagingTemplate simpMessagingTemplate){
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    public void sendTo(Object object, String path){
        this.simpMessagingTemplate.convertAndSend("/topic" + path, object);
    }
}