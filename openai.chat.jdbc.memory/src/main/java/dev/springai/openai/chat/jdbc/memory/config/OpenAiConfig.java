package dev.springai.openai.chat.jdbc.memory.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.memory.jdbc.JdbcChatMemoryRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class OpenAiConfig {

    @Bean
    ChatClient basicChatClient(ChatClient.Builder chatClientBuilder) {
        return chatClientBuilder.build();
    }

    @Bean
    ChatClient jdbcMemoryChatClient(ChatClient.Builder chatClientBuilder, JdbcTemplate jdbcTemplate) {
        return chatClientBuilder
                .defaultAdvisors(MessageChatMemoryAdvisor
                        .builder(createChatMemory(jdbcTemplate)).build())
                .build();
    }

    private ChatMemory createChatMemory(JdbcTemplate jdbcTemplate) {
        return MessageWindowChatMemory.builder()
                .chatMemoryRepository(JdbcChatMemoryRepository
                        .builder()
                        .jdbcTemplate(jdbcTemplate)
                        .build())
                .maxMessages(10)
                .build();
    }
}
