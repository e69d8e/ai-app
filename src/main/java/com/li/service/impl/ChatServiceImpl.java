package com.li.service.impl;

import com.li.assistant.Assistant;
import com.li.pojo.dto.UserMessage;
import com.li.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final Assistant assistant;

    @Override
    public Flux<String> chat(UserMessage userMessage) {
        return assistant.chat(userMessage.getMemoryId(), userMessage.getContent());
    }
}
