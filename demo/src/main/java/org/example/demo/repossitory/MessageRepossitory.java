package org.example.demo.repossitory;

import org.example.demo.model.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MessageRepossitory extends MongoRepository<Message, String> {
    // Custom methods, if needed
}
