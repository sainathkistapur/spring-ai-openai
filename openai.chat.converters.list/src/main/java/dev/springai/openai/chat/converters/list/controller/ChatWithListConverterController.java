package dev.springai.openai.chat.converters.list.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.converter.ListOutputConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/springai/openai")
@RequiredArgsConstructor
public class ChatWithListConverterController {

    private final ChatClient chatClient;

    private final ListOutputConverter listOutputConverter;

    @GetMapping("/prompt/simple")
    public String basicChat(@RequestBody String prompt) {
        return chatClient.prompt(prompt).call().content();
    }

    @GetMapping("/prompt/simple/with-list-converter")
    public List<String> promptWithListConverters(@RequestBody String prompt_statement) {
        prompt_statement = prompt_statement + " and {format}";

        PromptTemplate promptTemplate = new PromptTemplate(prompt_statement);
        Prompt prompt = promptTemplate.create(
                Map.of( "format", listOutputConverter.getFormat()));

        return listOutputConverter.convert(chatClient.prompt(prompt).call().content());
    }

}
