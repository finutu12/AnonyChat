package org.example.demo.controller;

import org.example.demo.model.ChatSession;
import org.example.demo.model.User;
import org.example.demo.repossitory.ChatSessionRepository;
import org.example.demo.service.ChatSessionService;
import org.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.util.List;

@RestController
@RequestMapping(value = "/chat")
public class ChatController {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final SecureRandom random = new SecureRandom();

    public static String generateRandomString(int length) {
        StringBuilder stringBuilder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            stringBuilder.append(randomChar);
        }
        return stringBuilder.toString();
    }

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
    public String returnName(@RequestBody Integer id) {
        User user = this.userService.findById(id);
        return "Hello User " + id;
    }
    @PostMapping("/freeSession")
    public ChatSession returnFreeChatSessions(@RequestBody Integer id) {
        List<ChatSession> freeChatSessions = this.chatSessionService.getFreeChatSession();
        User user = this.userService.findById(id);
        if(freeChatSessions.size() > 1){
            ChatSession firstChatSession = freeChatSessions.get(0);
            firstChatSession.getUsers().add(user);
            user.setChatSession(firstChatSession);
            this.chatSessionService.saveChatSession(firstChatSession);
            return firstChatSession;
        }
        else{
            ChatSession chatSession = new ChatSession(generateRandomString(25));
            chatSession = this.chatSessionService.saveChatSession(chatSession);
            chatSession.getUsers().add(user);
            user.setChatSession(chatSession);
            this.chatSessionService.saveChatSession(chatSession);
            return chatSession;
        }
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
