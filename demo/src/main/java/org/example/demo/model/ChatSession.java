package org.example.demo.model;

import org.apache.logging.log4j.message.Message;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "chat_sessions")
public class ChatSession {
    @Id
    private String id;
    private List<String> participantIds;
    private List<Message> messages;
    // Other session attributes

    // Constructors, getters, and setters
}
