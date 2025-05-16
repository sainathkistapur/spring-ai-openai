package dev.springai.openai.mcp.server.webmvc.tooling;

import dev.springai.openai.mcp.server.webmvc.domain.FlightInfo;
import dev.springai.openai.mcp.server.webmvc.service.FlightInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class FlightInfoTools {

    private final FlightInfoService flightInfoService;

    @Tool(name = "getAllFlights", description = "Get a list of all flights")
    public List<FlightInfo> getAllCourses() {
        return flightInfoService.getAllFlights();
    }
}
