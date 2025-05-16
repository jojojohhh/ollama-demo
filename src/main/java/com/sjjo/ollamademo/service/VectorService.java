package com.sjjo.ollamademo.service;

import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class VectorService {

    private final VectorStore vectorStore;

    public VectorService(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    public void init() {
        List<Document> documents = List.of(
                new Document("Spring AI rocks!! Spring AI rocks!! Spring AI rocks!! Spring AI rocks!! Spring AI rocks!!", Map.of("meta1", "meta1")),
                new Document("The World is Big and Salvation Lurks Around the Corner"),
                new Document("You walk forward facing the past and you turn back toward the future.", Map.of("meta2", "meta2")));

        vectorStore.add(documents);
    }

    public List<Document> retrieveSimilarDocument(String query) {
        return vectorStore.similaritySearch(SearchRequest.builder().query(query).topK(5).build());
    }

}
