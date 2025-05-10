package dev.springai.openai.mcp.client.postgresql.config;

import dev.springai.openai.mcp.client.postgresql.advisor.LoggingAdvisor;
import io.modelcontextprotocol.client.McpSyncClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.mcp.SyncMcpToolCallbackProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@Slf4j
public class OpenAiConfig {
    @Autowired
    private List<McpSyncClient> mcpSyncClients;

    @Bean
    public ChatClient basicChatClient(ChatClient.Builder chatClientBuilder) {
       log.info("\nConfigured MCP Clients");
        for (McpSyncClient client: mcpSyncClients) {
            log.info("\n" + client.getClientInfo().name());
        }
        var tools = new SyncMcpToolCallbackProvider(mcpSyncClients);
        return chatClientBuilder
                .defaultToolCallbacks(tools)
                .defaultAdvisors(new LoggingAdvisor())
                .build();
    }

}
