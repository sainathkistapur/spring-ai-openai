package dev.springai.openai.rag.etl.service.json;

import org.springframework.ai.document.Document;
import org.springframework.ai.reader.JsonReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MockDataJsonReader {

    private final Resource resource;

    MockDataJsonReader(@Value("classpath:data/MOCK_DATA.json") Resource resource) {
        this.resource = resource;
    }

    List<Document> loadJsonAsDocuments() {
        JsonReader jsonReader = new JsonReader(this.resource);
        return jsonReader.get();
    }
}
