package dev.springai.openai.rag.etl.controller;

import dev.springai.openai.rag.etl.service.json.JsonEtlPipelineService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/springai/openai/rag/etl/json-etl")
public class JsonEtlController {

    private final JsonEtlPipelineService jsonEtlPipeline;

    private final String RETURN_STRING = "Total records in the vector %d";
    private final String RETURN_STRING_ENRICHER = "Total records in the vector %d. All data should now have metadata populated from chat model";

    @GetMapping("/without-transformer")
    public String processJsonEtlWithoutTransformer() {
        Integer count = jsonEtlPipeline.processJsonEtlPipelineWithoutTransformer();
        return String.format(RETURN_STRING, count);
    }

    @GetMapping("/with-transformer")
    public String processJsonEtlWithTransformer() {
        Integer count = jsonEtlPipeline.processJsonEtlPipelineWithTransformer();
        return String.format(RETURN_STRING, count);
    }

    @GetMapping("/with-transformer-keyword-enricher")
    public String jsonEtlWithTransformerAndEnricher() {
        Integer count = jsonEtlPipeline.processJsonEtlPipelineWithTransformerAndKeywordEnricher();
        return String.format(RETURN_STRING_ENRICHER, count);
    }

    @GetMapping("/with-transformer-keyword-and-summary-enricher")
    public String jsonEtlWithTransformerAndKeywordAndSummaryEnricher() {
        Integer count = jsonEtlPipeline.pipelineWithTransformerAndKeywordEnricherAndSummaryEnricher();
        return String.format(RETURN_STRING_ENRICHER, count);
    }

    @GetMapping("/vector-count")
    public Integer getVectorCount() {
       return jsonEtlPipeline.getVectorCount();
    }
    @DeleteMapping("/clear-vector")
    public String clearVector() {
        jsonEtlPipeline.deleteAll();
        return "DONE";
    }
}
