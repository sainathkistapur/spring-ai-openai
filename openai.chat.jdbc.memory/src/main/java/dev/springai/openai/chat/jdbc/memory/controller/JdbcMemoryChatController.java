package dev.springai.openai.chat.jdbc.memory.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.web.bind.annotation.*;

import static org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor.CHAT_MEMORY_CONVERSATION_ID_KEY;

@RestController
@RequestMapping("/springai/openai/chat/jdbc-memory")
@RequiredArgsConstructor
public class JdbcMemoryChatController {

    private final ChatClient basicChatClient;

    private final ChatClient jdbcMemoryChatClient;

    @GetMapping("/prompt-with-no-memory")
    public String chatWithNoMemory(@RequestBody String prompt,
                                   @RequestHeader(value = "conversationId") String conversationId) {
        return basicChatClient.prompt(prompt).call().content();
    }

    @GetMapping("/prompt-with-jdbc-memory")
    public String chatWithJdbcMemory(@RequestBody String prompt,
                                     @RequestHeader(value = "conversationId") String conversationId) {
        return jdbcMemoryChatClient.prompt(prompt).advisors(
                advisorSpec -> advisorSpec.param(CHAT_MEMORY_CONVERSATION_ID_KEY, conversationId))
                .call().content();
    }

}
