package org.example.demo.repossitory;

import org.example.demo.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepossitory extends MongoRepository<User, String> {
    // Custom methods, if needed
}
