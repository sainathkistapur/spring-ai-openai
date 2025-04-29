package dev.springai.openai.multimodel.transcribe.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.audio.transcription.AudioTranscriptionPrompt;
import org.springframework.ai.audio.transcription.AudioTranscriptionResponse;
import org.springframework.ai.openai.OpenAiAudioTranscriptionModel;
import org.springframework.ai.openai.OpenAiAudioTranscriptionOptions;
import org.springframework.ai.openai.api.OpenAiAudioApi;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/springai/openai/multimodel")
@RequiredArgsConstructor
@Slf4j
public class TranscribeController {

    private final OpenAiAudioTranscriptionModel openAiAudioTranscriptionModel;

    @GetMapping("/transcribe")
    public String audioToText() {
        OpenAiAudioTranscriptionOptions openAiAudioTranscriptionOptions =
                OpenAiAudioTranscriptionOptions.builder()
                        .language("en")
                        .temperature(0f)
                        .responseFormat(OpenAiAudioApi.TranscriptResponseFormat.TEXT)
                        .build();

        AudioTranscriptionPrompt audioTranscriptionPrompt = new AudioTranscriptionPrompt(
                new ClassPathResource("audio/harvard.wav"), openAiAudioTranscriptionOptions);

        AudioTranscriptionResponse audioTranscriptionResponse =
                openAiAudioTranscriptionModel.call(audioTranscriptionPrompt);

        return audioTranscriptionResponse.getResult().getOutput();
    }
}
