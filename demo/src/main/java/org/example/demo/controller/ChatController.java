package org.example.demo.controller;

import org.example.demo.model.User;
import org.example.demo.repossitory.ChatSessionRepository;
import org.example.demo.service.ChatSessionService;
import org.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/chat")
public class ChatController {

    private final ChatSessionService chatSessionService;
    private final UserService userService;

    @Autowired
    public ChatController(final ChatSessionService chatSessionService,
                              final UserService userService) {
        this.chatSessionService = chatSessionService;
        this.userService = userService;
    }

    @GetMapping("/get")
    public String getUserByAuthentication() {
        return "test";
    }

    @PostMapping("/greet")
    public String returnName(@RequestBody String name) {
        return "Hello " + name;
    }

    @GetMapping("/getGreet")
    public String returnNameGet(@RequestParam String name) {
        return "Hello " + name;
    }

    @GetMapping("/less2")
    public List<User> less2(@RequestParam Integer nr) {
        final List<User> users = this.userService.findAllLessThen(nr);
        return users;
    }

    @GetMapping("/findByID")
    public List<User> findByID(@RequestParam Integer id) {
        final List<User> users = this.userService.findAllByChatSessionId(id);
        return users;
    }
}
