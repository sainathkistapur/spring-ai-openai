package dev.springai.openai.mcp.client.filesystem.controller;

import io.modelcontextprotocol.client.McpSyncClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/springai/openai/mcp/client/filesystem")
@RequiredArgsConstructor
@Slf4j
public class McpClientController {

    private final ChatClient basicChatClient;

    @GetMapping("/basic-chat")
    public String mcpBasicChat(@RequestBody String prompt) {
        try {
            return basicChatClient.prompt()
                    .user(prompt)
                    .call()
                    .content();
        } catch (Exception e) {
            log.error("Failed to perform the request: " + e.getSuppressed());
            return e.getMessage();
        }
    }
}
