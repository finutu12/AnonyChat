package org.example.demo.controller;

import com.mysql.cj.x.protobuf.Mysqlx;
import org.example.demo.model.ChatSession;
import org.example.demo.model.LeaveChatRequestBody;
import org.example.demo.model.Message;
import org.example.demo.model.User;
import org.example.demo.repossitory.ChatSessionRepository;
import org.example.demo.service.ChatSessionService;
import org.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Key;
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

    @PostMapping("/leave")
    public ResponseEntity<HttpStatus> LeaveChat(@RequestBody User user) {
        User u = this.userService.findById(user.getId());
        ChatSession chatSession = this.chatSessionService.findByUser(user.getId());
        if(u == null || chatSession == null){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        chatSession.getUsers().remove(u);
        u.setChatSession(null);
        this.chatSessionService.saveChatSession(chatSession);
        this.userService.saveUser(u);
        if(chatSession.getUsers().size() < 1){
            this.chatSessionService.delete(chatSession);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/freeSession")
    public ResponseEntity<ChatSession> returnFreeChatSessions(@RequestBody Integer id) {
        List<ChatSession> freeChatSessions = this.chatSessionService.getFreeChatSession();
        User user = this.userService.findById(id);
        if (freeChatSessions.size() > 0) {
            ChatSession firstChatSession = freeChatSessions.get(0);
            firstChatSession.getUsers().add(user);
            user.setChatSession(firstChatSession);
            this.chatSessionService.saveChatSession(firstChatSession);
            return new ResponseEntity<>(firstChatSession, HttpStatus.OK);
        } else {
            ChatSession chatSession = new ChatSession(generateRandomString(25));
            chatSession = this.chatSessionService.saveChatSession(chatSession);
            chatSession.getUsers().add(user);
            user.setChatSession(chatSession);
            this.chatSessionService.saveChatSession(chatSession);
            return new ResponseEntity<>(chatSession, HttpStatus.OK);
        }
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
