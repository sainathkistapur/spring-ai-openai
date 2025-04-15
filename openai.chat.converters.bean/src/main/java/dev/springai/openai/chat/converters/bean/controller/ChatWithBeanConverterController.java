package dev.springai.openai.chat.converters.bean.controller;

import dev.springai.openai.chat.converters.bean.dto.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/springai/openai")
@RequiredArgsConstructor
public class ChatWithBeanConverterController {

    private final ChatClient chatClient;

    @GetMapping("/prompt/simple")
    public String basicChat(@RequestBody String prompt) {
        return chatClient.prompt(prompt).call().content();
    }

    @GetMapping("/prompt/simple/with-bean-converter")
    public Person findPersonWithBeanConverter(@RequestBody String prompt_statement) {
        prompt_statement = prompt_statement + " and {format}";

        BeanOutputConverter<Person> beanOutputConverter = new BeanOutputConverter(Person.class);

        PromptTemplate promptTemplate = new PromptTemplate(prompt_statement);
        Prompt prompt = promptTemplate.create(
                Map.of( "format", beanOutputConverter.getFormat()));

        return beanOutputConverter.convert(chatClient.prompt(prompt).call().content());
    }
}

