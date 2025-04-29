package dev.springai.openai.multimodel.image.to.text.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/springai/openai/multimodel")
@RequiredArgsConstructor
public class ImageToTextController {

    private final ChatClient chatClient;

    @GetMapping("/image-to-text")
    public String explainImage() {
        String response = chatClient.prompt().user(
                        promptUserSpec -> promptUserSpec.text("Describe this image?")
                                .media(MimeTypeUtils.IMAGE_PNG,
                                        new ClassPathResource("images/apple-playing-cricket.png"))
                ).call().content();

        return response;
    }


}
