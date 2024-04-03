package org.example.demo.repossitory;

import org.example.demo.model.ChatSession;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChatSessionRepossitory extends MongoRepository<ChatSession, String> {
    // Custom methods, if needed
}