package dev.springai.openai.rag.file.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.RetrievalAugmentationAdvisor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/springai/openai/rag/file")
@RequiredArgsConstructor
public class RagFileController {

    private final ChatClient chatClient;

    private final RetrievalAugmentationAdvisor retrievalAugmentationAdvisor;


    @GetMapping("/without-rag")
    public String withoutRag(@RequestBody String prompt) {
        return chatClient.prompt().user(prompt).call().content();
    }

    @GetMapping("/with-rag")
    public String witRag(@RequestBody String prompt) {
        return chatClient.prompt().user(prompt).advisors(retrievalAugmentationAdvisor).call().content();
    }
}
