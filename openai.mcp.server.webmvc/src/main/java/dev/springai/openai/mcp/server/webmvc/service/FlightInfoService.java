package dev.springai.openai.mcp.server.webmvc.service;

import dev.springai.openai.mcp.server.webmvc.domain.FlightInfo;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightInfoService {

    private final List<FlightInfo> flightData;

    public List<FlightInfo> getAllFlights() {
        return flightData;
    }

}
