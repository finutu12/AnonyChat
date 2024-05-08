package org.example.demo.service;

import org.example.demo.model.User;
import org.example.demo.repossitory.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User getUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<User> findAllLessThen(Integer nr) {
        return this.userRepository.findAllByIdLessThan(nr);
    }

    public List<User> findAllByChatSessionId(Integer id) {
        return this.userRepository.findAllByChatSessionId(id);
    }
}
