package dev.springai.openai.chat.advisors.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/springai/openai/advisors")
@RequiredArgsConstructor
@Slf4j
public class BasicChatForObservabilityTestController {

    private final ChatClient loggingAdvisorChatClient;

    private final ChatClient safeGuardAdvisorChatClient;

    private final ChatClient mixedAdvisorChatClient;

    @GetMapping("/logging")
    public String basicChat(@RequestBody String prompt) {
        return loggingAdvisorChatClient.prompt(prompt).call().content();
    }

    @GetMapping("/safeguard")
    public String basicChatWithSafeGuard(@RequestBody String prompt) {
        return safeGuardAdvisorChatClient.prompt(prompt).call().content();
    }

    @GetMapping("/mixed-advisors")
    public String basicChatWithMixedAdvisors(@RequestBody String prompt) {
        return mixedAdvisorChatClient.prompt(prompt).call().content();
    }
}
