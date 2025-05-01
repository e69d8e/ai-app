package com.li.controller;

import com.li.pojo.dto.UserMessage;
import com.li.service.ChatService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@Controller
@RestController
@RequestMapping("/chat")
@Tag(name = "聊天")
public class ChatController {
    @Autowired
    private ChatService chatService;

    @PostMapping
    @Operation(summary = "聊天")
    public Flux<String> chat(@RequestBody UserMessage userMessage) {
        return chatService.chat(userMessage);
    }
}
