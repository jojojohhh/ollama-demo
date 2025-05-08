package com.sjjo.ollamademo.controller;

import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.Map;

@RestController
public class OllamaDemoController {

    private final OllamaChatModel ollamaChatModel;

    public OllamaDemoController(OllamaChatModel ollamaChatModel) {
        this.ollamaChatModel = ollamaChatModel;
    }

    @PostMapping("/ai/generate")
    public Map<String,String> generate(@RequestBody String message) {
        return Map.of("generation", this.ollamaChatModel.call(message));
    }

    @PostMapping("/ai/generateStream")
    public Flux<ChatResponse> generateStream(@RequestBody String message) {
        Prompt prompt = new Prompt(new UserMessage(message));
        return this.ollamaChatModel.stream(prompt);
    }

}
