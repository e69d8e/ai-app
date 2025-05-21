package com.li.server.service.impl;

import com.li.assistant.Assistant;
import com.li.server.mapper.ChatMapper;
import com.li.pojo.dto.UserMessageDTO;
import com.li.pojo.entity.Session;
import com.li.server.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final Assistant assistant;
    private final ChatMapper chatMapper;

    @Override
    public Flux<String> chat(UserMessageDTO userMessageDTO) {
        // 根据memoryId查询会话
        Session session = chatMapper.selectById(userMessageDTO.getMemoryId());
        if (session == null) {
            Session s = Session.builder().
                    id(userMessageDTO.getMemoryId()).
                    userId(userMessageDTO.getUserId()).
                    time(LocalDateTime.now()).
                    name(userMessageDTO.getContent().length() > 64
                            ? userMessageDTO.getContent().substring(0, 64)
                            : userMessageDTO.getContent()).
                    build();
            chatMapper.insert(s);
        }
        return assistant.chat(userMessageDTO.getMemoryId(), userMessageDTO.getContent());
    }
}
