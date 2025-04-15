package dev.springai.openai.chat.observability.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/springai/openai/basic-chat/observability")
@RequiredArgsConstructor
@Slf4j
public class BasicChatForObservabilityTestController {

    private final ChatClient chatClient;

    @GetMapping()
    public String basicChat(@RequestBody String prompt) {
        log.info("Request received for Observability check");
        return chatClient.prompt(prompt).call().content();
    }
}
