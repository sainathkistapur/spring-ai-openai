package dev.springai.openai.tool.calling.controller;

import dev.springai.openai.tool.calling.config.WeatherTooConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/springai/openai/toolcalling")
@RequiredArgsConstructor
public class WeatherToolCallingController {

    private final ChatClient chatClient;

    private final String WEATHER_FORECAST_PROMPT = """
                What is the weather forecast in location {city}? If necessary use the Lat and Lon in two digit positive 
                integer format {city} give no space after comma. do not use any words
                """;

    @GetMapping("/current-weather-without-tool-calling")
    public String getDateTimeWithoutToolCalling() {
        return chatClient.prompt("What is the weather forecast in Boston?")
                .call()
                .content();
    }

    @GetMapping("/current-weather")
    public String getDateTime(@RequestParam String city) {

        PromptTemplate promptTemplate = PromptTemplate.builder().template(WEATHER_FORECAST_PROMPT).build();
        Prompt prompt = promptTemplate.create(Map.of("city" , city));
            return chatClient.prompt(prompt)
            .toolNames(WeatherTooConfig.WEATHER_FORECAST)
            .call()
            .content();
    }


}
