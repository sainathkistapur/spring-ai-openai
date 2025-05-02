package dev.springai.openai.chat.in.memory.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.web.bind.annotation.*;

import static org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor.CHAT_MEMORY_CONVERSATION_ID_KEY;

@RestController
@RequestMapping("/springai/openai/chat/in-memory")
@RequiredArgsConstructor
public class InMemoryChatController {

    private final ChatClient basicChatClient;

    private final ChatClient inMemoryChatClient;

    @GetMapping("/prompt-with-no-memory")
    public String chatWithNoMemory(@RequestBody String prompt,
                                   @RequestHeader(value = "conversationId") String conversationId) {
        return basicChatClient.prompt(prompt).call().content();
    }

    @GetMapping("/prompt-with-in-memory")
    public String chatWithInMemory(@RequestBody String prompt,
                                   @RequestHeader(value = "conversationId") String conversationId) {
        return inMemoryChatClient.prompt(prompt).advisors(
                advisorSpec -> advisorSpec.param(CHAT_MEMORY_CONVERSATION_ID_KEY, conversationId))
                .call().content();
    }

}
