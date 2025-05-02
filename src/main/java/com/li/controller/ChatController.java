package com.li.controller;

import com.li.pojo.dto.UserMessageDTO;
import com.li.service.ChatService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@Controller
@RestController
@RequestMapping("/chat")
@Tag(name = "聊天")
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;

    @PostMapping(produces = "text/stream;charset=utf-8")
    @Operation(summary = "聊天")
    public Flux<String> chat(@RequestBody UserMessageDTO userMessageDTO) {
        return chatService.chat(userMessageDTO);
    }
}
