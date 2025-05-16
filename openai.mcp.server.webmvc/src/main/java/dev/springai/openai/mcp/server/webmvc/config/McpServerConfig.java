package dev.springai.openai.mcp.server.webmvc.config;

import dev.springai.openai.mcp.server.webmvc.tooling.FlightInfoTools;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@RequiredArgsConstructor
public class McpServerConfig {

    private final FlightInfoTools flightInfoTools;

    @Bean
    public ToolCallbackProvider flightInfoToolsCallback() {
        return MethodToolCallbackProvider.builder().toolObjects(flightInfoTools).build();
    }
}
