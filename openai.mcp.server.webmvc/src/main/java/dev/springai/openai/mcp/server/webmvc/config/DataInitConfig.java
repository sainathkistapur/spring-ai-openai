package dev.springai.openai.mcp.server.webmvc.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.springai.openai.mcp.server.webmvc.domain.FlightInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.InputStream;
import java.util.List;

@Configuration
@Slf4j
public class DataInitConfig {

    @Bean
    public List<FlightInfo> flightData() throws Exception {
        try {
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<List<FlightInfo>> typeReference = new TypeReference<List<FlightInfo>>(){};
            InputStream inputStream = TypeReference.class.getResourceAsStream("/data/flight-data.json");
            List<FlightInfo> flightInfoList = mapper.readValue(inputStream,typeReference);

            log.info("Total flight data loaded:", flightInfoList.size());

            return flightInfoList;
        } catch (Exception e) {
            log.error("error : {}", e.getMessage());
            throw e;
         }

    }
}
