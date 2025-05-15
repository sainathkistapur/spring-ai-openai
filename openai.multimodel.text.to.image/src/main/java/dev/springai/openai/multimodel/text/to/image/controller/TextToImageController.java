package dev.springai.openai.multimodel.text.to.image.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.image.Image;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/springai/openai/multimodel")
@RequiredArgsConstructor
@Slf4j
public class TextToImageController {

    private final OpenAiImageModel openAiImageModel;
    @GetMapping("/text-to-image")
    public String generateImage(@RequestBody String prompt) {

        ImagePrompt imagePrompt = new ImagePrompt(prompt,
                OpenAiImageOptions.builder()
                        .height(1024)
                        .width(1024)
                        .quality("hd")
                        .build());

        Image image = openAiImageModel.call(imagePrompt).getResult().getOutput();

        return image.getUrl();
    }
}
