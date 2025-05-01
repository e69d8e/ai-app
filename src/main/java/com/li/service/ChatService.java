package com.li.service;

import com.li.pojo.dto.UserMessage;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

public interface ChatService {
    Flux<String> chat(UserMessage userMessage);
}
