package dev.springai.openai.rag.pgvector.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/springai/openai/rag/pgvector")
public class RagContextSupplyController {

    private final VectorStore vectorStore;

    private final ChatClient basicChatClient;

    private final ChatClient ragChatClientWithQaAdvisor;

    private final ChatClient ragChatClientWithRagAdvisor;

    private final ChatModel chatModel;

    private static final String PROMPT_STRING = """
             Use the information from the DOCUMENTS section to provide accurate answers. If unsure or if the answer 
             isn't found in the DOCUMENTS section, simply state that you don't know the answer.
            
            QUESTION:
            {input}
            
            DOCUMENTS:
            {documents}
            """;

    @GetMapping("/without-rag-context")
    public String withoutRag(@RequestBody String prompt) {
        return basicChatClient.prompt().user(prompt).call().content();
    }

    @GetMapping("/with-rag-prompt-template")
    public String withRagPromptTemplate(@RequestBody String input) {

        PromptTemplate promptTemplate =PromptTemplate.builder()
                .template(PROMPT_STRING)
                .variables(Map.of("input", input, "documents", getContextDataFromVectorStore(input)))
                .build();
        return chatModel.call(promptTemplate.create()).getResult().getOutput().getText();
    }

    @GetMapping("/with-rag-qa-advisor")
    public String withRagWithQaAdvisor(@RequestBody String input) {
        return ragChatClientWithQaAdvisor.prompt().user(input).call().content();
    }

    @GetMapping("/with-rag-rag-advisor")
    public String withRagWithRagAdvisor(@RequestBody String input) {
        return ragChatClientWithRagAdvisor.prompt().user(input).call().content();
    }

    private String getContextDataFromVectorStore(String context) {
        List<Document> documents =  vectorStore.similaritySearch(
                SearchRequest.builder().query(context).build());

        log.info("Number of documents that are in the context: " + documents.size());
        return documents.stream().map(doc -> doc.getText().toString()).collect(Collectors.joining());
    }
}
