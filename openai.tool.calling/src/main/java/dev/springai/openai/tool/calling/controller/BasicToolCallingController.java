package dev.springai.openai.tool.calling.controller;

import dev.springai.openai.tool.calling.tools.datetime.DateTimeTools;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/springai/openai/toolcalling")
@RequiredArgsConstructor
public class BasicToolCallingController {

    private final ChatClient chatClient;

    private final DateTimeTools dateTimeTools;

    @GetMapping("/basic-chat")
    public String basicChat(@RequestBody String prompt) {
        return chatClient.prompt(prompt).call().content();
    }

    @GetMapping("/date-time-without-tool-calling")
    public String getDateTimeWithoutToolCalling() {
        return chatClient.prompt("What current date and time?").call().content();
    }

    @GetMapping("/date-time")
    public String getDateTime() {
        return chatClient.prompt("What current date and time?").tools(dateTimeTools).call().content();
    }

    @GetMapping("/set-alarm")
    public String setAlarm(@RequestParam String time) {
        return chatClient.prompt("Can you set an alarm "+ time +" minutes from now?").tools(dateTimeTools).call().content();
    }

    @GetMapping("/tomorrow-day")
    public String tomorrowDay() {
        return chatClient.prompt("what day is tomorrow?").tools(dateTimeTools).call().content();
    }


}
