package com.li.assistant;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.service.spring.AiServiceWiringMode;
import reactor.core.publisher.Flux;

@AiService(
        wiringMode = AiServiceWiringMode.EXPLICIT,
        chatMemoryProvider = "chatMemoryProvider",
        streamingChatModel = "qwenStreamingChatModel"
)
public interface Assistant {
    @SystemMessage(fromResource = "system-prompt.txt")
    Flux<String> chat(@MemoryId String memoryId, @UserMessage String message);
}
