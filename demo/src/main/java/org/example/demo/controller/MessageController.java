package org.example.demo.controller;

import org.example.demo.model.ChatSession;
import org.example.demo.model.Message;
import org.example.demo.model.MessageDTO;
import org.example.demo.model.User;
import org.example.demo.service.ChatSessionService;
import org.example.demo.service.DTOUtils;
import org.example.demo.service.MessageService;
import org.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/message")
public class MessageController {
    private final MessageService messageService;
    private final UserService userService;
    private final ChatSessionService chatSessionService;

    @Autowired
    public MessageController(final MessageService messageService,
                          final UserService userService, final ChatSessionService chatSessionService) {
        this.messageService = messageService;
        this.userService = userService;
        this.chatSessionService = chatSessionService;
    }


    @PostMapping("/sendMessage")
    public Message saveMessage(@RequestBody Message message) {
        return this.messageService.saveMessage(message);
    }

    @PostMapping("/getMessages")
    public List<MessageDTO> getMessages(@RequestBody Message message) {

        return DTOUtils.mapList(this.messageService.findByUserIDAndChatSessionID(message.getUser().getId(), message.getChatSession().getId()), MessageDTO.class);
    }

}
