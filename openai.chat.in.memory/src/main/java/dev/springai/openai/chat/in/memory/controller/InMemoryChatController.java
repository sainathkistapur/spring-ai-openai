package dev.springai.openai.chat.in.memory.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/springai/openai/chat/in-memory")
@RequiredArgsConstructor
public class InMemoryChatController {

    private final ChatClient basicChatClient;

    private final ChatClient inMemoryChatClient;

    @GetMapping("/prompt-with-no-memory")
    public String chatWithNoMemory(@RequestBody String prompt) {
        return basicChatClient.prompt(prompt).call().content();
    }

    @GetMapping("/prompt-with-in-memory")
    public String chatWithInMemory(@RequestBody String prompt) {
        return inMemoryChatClient.prompt(prompt).call().content();
    }

}
