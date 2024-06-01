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


    @PostMapping("/sendMessage")
    public Message saveMessage(@RequestBody Message message) {
        final Message m = this.messageService.saveMessage(message);
        this.stompMessageService.sendTo(message.getContent(),"/greetings");
        return m;
    }

    @PostMapping("/getMessages")
    public List<MessageDTO> getMessages(@RequestBody Integer chatSessionId) {
        return DTOUtils.mapList(this.messageService.findByChatSessionID(chatSessionId), MessageDTO.class);
    }

}
