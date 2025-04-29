package dev.springai.openai.multimodel.text.to.speech.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.openai.audio.speech.SpeechModel;
import org.springframework.ai.openai.audio.speech.SpeechPrompt;
import org.springframework.ai.openai.audio.speech.SpeechResponse;
import org.springframework.ai.openai.audio.speech.StreamingSpeechModel;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/springai/openai/multimodel")
@RequiredArgsConstructor
@Slf4j
public class TextToSpeechController {

    private final SpeechModel speechModel;

    private final StreamingSpeechModel streamingSpeechModel;

    @GetMapping("/text-to-speech")
    public ResponseEntity<Resource> textToSpeech(@RequestBody String inputText) {
        SpeechPrompt speechPrompt = new SpeechPrompt(inputText);
        SpeechResponse response = this.speechModel.call(speechPrompt);
        byte[] audioBytes = response.getResult().getOutput();

        ByteArrayResource byteArrayResource = new ByteArrayResource(audioBytes);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(new ByteArrayResource(audioBytes).contentLength())
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        ContentDisposition.attachment().filename("audio"+ LocalDateTime.now()+".mp3")
                                .build().toString())
                .body(byteArrayResource);
    }

    @GetMapping("/text-to-speech-stream")
    Flux<byte[]> streamingSpeech(@RequestBody String inputText) {
        return streamingSpeechModel.stream(inputText);
    }
}
