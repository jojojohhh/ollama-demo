package com.sjjo.ollamademo;

import com.sjjo.ollamademo.service.VectorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.document.Document;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class OllamaDemoApplication implements CommandLineRunner {

    private final VectorService vectorService;

    public OllamaDemoApplication(VectorService vectorService) {
        this.vectorService = vectorService;
    }

    public static void main(String[] args) {
        SpringApplication.run(OllamaDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        vectorService.init();

        for (Document document : vectorService.retrieveSimilarDocument("Spring")) {
            log.info("document content: {}", document.getFormattedContent());
        }

    }
}
