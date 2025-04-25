package dev.springai.openai.rag.pgvector.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.reader.ExtractedTextFormatter;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.reader.pdf.config.PdfDocumentReaderConfig;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.simple.JdbcClient;

import java.io.File;
import java.util.List;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class PgVectorConfig {

    @Value("classpath:/rag-data/HMT_Spring_Statement_2025.pdf")
    private Resource ragPdfResource;

    private static final String VECTOR_STORE_COUNT_QUERY = "select COUNT(*) from vector_store";

    // As of now we cannot query the VectorStore to check the count. So we are using JDBC client.
    private final JdbcClient jdbcClient;

    private final VectorStore vectorStore;

    @Bean
    VectorStore simpleVectorStore(EmbeddingModel embeddingModel) {

        if (getVectorStoreCount() > 0) {
           log.info("Vector Store is already populated.");
        } else {
            log.info("Vector Store is empty. Loading the data...");
            vectorStore.accept(TokenTextSplitter.builder().build().apply(getDocsFromPdf()));
            log.info("Loaded " + getVectorStoreCount() + " records.");
        }
        log.info("Application is Ready...");
        return vectorStore;
    }

    private Integer getVectorStoreCount() {
       return jdbcClient.sql(VECTOR_STORE_COUNT_QUERY)
                .query(Integer.class)
                .single();
    }

    private List<Document> getDocsFromPdf() {
        PagePdfDocumentReader pdfReader = new PagePdfDocumentReader(ragPdfResource,
                PdfDocumentReaderConfig.builder()
                        .withPageTopMargin(0)
                        .withPageExtractedTextFormatter(ExtractedTextFormatter.builder()
                                .withNumberOfTopTextLinesToDelete(0)
                                .build())
                        .withPagesPerDocument(1)
                        .build());

        return pdfReader.read();
    }

}
