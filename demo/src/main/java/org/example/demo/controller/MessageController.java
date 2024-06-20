package org.example.demo.controller;

import org.example.demo.model.ChatSession;
import org.example.demo.model.Message;
import org.example.demo.model.MessageDTO;
import org.example.demo.model.User;
import org.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/message")
public class MessageController {
    private final MessageService messageService;
    private final UserService userService;
    private final ChatSessionService chatSessionService;
    private final StompMessageService stompMessageService;

    @Autowired
    public MessageController(final MessageService messageService,
                             final UserService userService,
                             final ChatSessionService chatSessionService,
                             final StompMessageService stompMessageService) {
        this.messageService = messageService;
        this.userService = userService;
        this.chatSessionService = chatSessionService;
        this.stompMessageService = stompMessageService;
    }


    @GetMapping("/sendMessage")
    public Message saveMessage(@RequestParam Integer chatSessionId, @RequestParam Integer userId, @RequestParam String messageContent) {
        User user = this.userService.findById(userId);
        ChatSession chatSession = this.chatSessionService.findById(chatSessionId);
        Message message = new Message(messageContent, user, chatSession);
        return this.messageService.saveMessage(message);
    }

    @PostMapping("/getMessages")
    public List<MessageDTO> getMessages(@RequestBody Integer chatSessionId) {
        return DTOUtils.mapList(this.messageService.findByChatSessionID(chatSessionId), MessageDTO.class);
    }

}
