package org.example.demo.service;

import org.example.demo.model.User;
import org.example.demo.repossitory.UserRepossitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepossitory userRepossitory;

    @Autowired
    public UserService(UserRepossitory userRepossitory) {
        this.userRepossitory = userRepossitory;
    }

    public User saveUser(User user) {
        return userRepossitory.save(user);
    }

    public User getUserById(String id) {
        return userRepossitory.findById(id).orElse(null);
    }

    // Other methods as needed
}
