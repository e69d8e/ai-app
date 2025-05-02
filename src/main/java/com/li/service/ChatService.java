package com.li.service;

import com.li.pojo.dto.UserMessageDTO;
import reactor.core.publisher.Flux;

public interface ChatService {
    Flux<String> chat(UserMessageDTO userMessageDTO);
}
