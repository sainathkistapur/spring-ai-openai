package dev.springai.openai.rag.etl.service.json;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.document.Document;
import org.springframework.ai.model.transformer.KeywordMetadataEnricher;
import org.springframework.ai.model.transformer.SummaryMetadataEnricher;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.ai.model.transformer.SummaryMetadataEnricher.*;

@Component
@RequiredArgsConstructor
public class JsonEtlPipelineService {

    private final ChatModel chatModel;

    // Writer
    private final VectorStore vectorStore;

    //Note: as of writing this code there is no methods to manage the vector managing operations in the VectorStore
    // like count or delete all. so have to use JdbcClient
    private final JdbcClient jdbcClient;

    private final MockDataJsonReader employeeJsonReader;
   public Integer processJsonEtlPipelineWithoutTransformer() {
       // Document Reader
       List<Document> employeeDocuments = employeeJsonReader.loadJsonAsDocuments();

       // ETL Pipeline - Reader -> Writer
       vectorStore.write(employeeDocuments);

       return jdbcClient.sql("select COUNT(*) from vector_store")
               .query(Integer.class)
               .single();
   }

    public Integer processJsonEtlPipelineWithTransformer() {
        // Document Reader
        List<Document> employeeDocuments = employeeJsonReader.loadJsonAsDocuments();

        //Transformer
        TokenTextSplitter tokenTextSplitter = TokenTextSplitter.builder()
                .withChunkSize(100)
                .withMinChunkSizeChars(40)
                .withMinChunkLengthToEmbed(10)
                .withMaxNumChunks(500)
                .withKeepSeparator(true)
                .build();

        // ETL Pipeline - Reader -> Transformer -> Writer
        vectorStore.write(tokenTextSplitter.apply(employeeDocuments));

        return jdbcClient.sql("select COUNT(*) from vector_store")
                .query(Integer.class)
                .single();
    }

    public Integer processJsonEtlPipelineWithTransformerAndKeywordEnricher() {
        // Document Reader
        List<Document> employeeDocuments = employeeJsonReader.loadJsonAsDocuments();

        //Transformer
        TokenTextSplitter tokenTextSplitter = TokenTextSplitter.builder()
                .withChunkSize(100)
                .withMinChunkSizeChars(40)
                .withMinChunkLengthToEmbed(10)
                .withMaxNumChunks(500)
                .withKeepSeparator(true)
                .build();

        //KeywordEnricher
        KeywordMetadataEnricher keywordMetadataEnricher = new KeywordMetadataEnricher(chatModel, 1);

        // ETL Pipeline - Reader -> Transformer -> Writer
        vectorStore.write(keywordMetadataEnricher.apply(tokenTextSplitter.apply(employeeDocuments)));

        return jdbcClient.sql("select COUNT(*) from vector_store")
                .query(Integer.class)
                .single();
    }

    public Integer pipelineWithTransformerAndKeywordEnricherAndSummaryEnricher() {
        // Document Reader
        List<Document> employeeDocuments = employeeJsonReader.loadJsonAsDocuments();

        //Transformer
        TokenTextSplitter tokenTextSplitter = TokenTextSplitter.builder()
                .withChunkSize(100)
                .withMinChunkSizeChars(40)
                .withMinChunkLengthToEmbed(10)
                .withMaxNumChunks(500)
                .withKeepSeparator(true)
                .build();

        //KeywordMetadataEnricher
        KeywordMetadataEnricher keywordMetadataEnricher = new KeywordMetadataEnricher(chatModel, 1);

        // SummaryMetadataEnricher
        SummaryMetadataEnricher summaryMetadataEnricher = new SummaryMetadataEnricher(chatModel,
                List.of(SummaryType.PREVIOUS, SummaryType.CURRENT, SummaryType.NEXT));

        // ETL Pipeline - Reader -> Transformer -> Writer
        vectorStore.write(summaryMetadataEnricher
                .apply(keywordMetadataEnricher.apply(tokenTextSplitter.apply(employeeDocuments))));

        return jdbcClient.sql("select COUNT(*) from vector_store")
                .query(Integer.class)
                .single();
    }



        public Integer getVectorCount() {
            return jdbcClient.sql("select COUNT(*) from vector_store")
                   .query(Integer.class)
                   .single();
       }

        public void deleteAll() {
            jdbcClient.sql("delete from vector_store where id is not null")
                    .update();
        }

    }