package com.li;

import com.li.assistant.Assistant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;

@SpringBootTest
public class ChatTest {

    @Autowired
    private Assistant assistant;

    @Test
    public void testChat() {
        Flux<String> result = assistant.chat("1", "你好");
        result.subscribe(System.out::println);
    }
}
