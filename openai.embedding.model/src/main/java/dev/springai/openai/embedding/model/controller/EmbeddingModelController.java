package dev.springai.openai.embedding.model.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.embedding.EmbeddingRequest;
import org.springframework.ai.embedding.EmbeddingResponse;
import org.springframework.ai.openai.OpenAiEmbeddingOptions;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/springai/openai/embedding")
@RequiredArgsConstructor()
public class EmbeddingModelController {

    private final EmbeddingModel embeddingModel;

    @GetMapping("/simple")
    public Map embed(@RequestBody String prompt) {
        EmbeddingResponse embeddingResponse = this.embeddingModel.embedForResponse(List.of(prompt));
        return Map.of("embedding", embeddingResponse);
    }

    @GetMapping("/custom")
    public Map customsEmbed(@RequestBody String prompt) {
        EmbeddingResponse embeddingResponse = this.embeddingModel.call(
                new EmbeddingRequest(List.of(prompt),
                OpenAiEmbeddingOptions.builder()
                        .model("text-embedding-3-small")
                        .build()));
        return Map.of("embedding", embeddingResponse);
    }
}
