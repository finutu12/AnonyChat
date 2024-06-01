package org.example.demo.service;

import org.example.demo.model.Message;
import org.example.demo.repossitory.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    private final MessageRepository messageRepository;
    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }

    public List<Message> findByUserIDAndChatSessionID(Integer UserID, Integer ChatSessionID) {
        return messageRepository.findAllByUserIdAndChatSessionId(UserID, ChatSessionID);
    }

    public List<Message> findByChatSessionID(Integer ChatSessionID) {
        return messageRepository.findAllByChatSessionId(ChatSessionID);
    }
}
