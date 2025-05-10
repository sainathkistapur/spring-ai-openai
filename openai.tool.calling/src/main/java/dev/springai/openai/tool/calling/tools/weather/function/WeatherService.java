package dev.springai.openai.tool.calling.tools.weather.function;

import dev.springai.openai.tool.calling.tools.weather.domain.WeatherRequest;
import dev.springai.openai.tool.calling.tools.weather.domain.WeatherResponse;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.RestClient;

import java.util.function.Function;

public class WeatherService implements Function<WeatherRequest, WeatherResponse> {

    private RestClient restClient;

    private final String WEATHER_API_URL = "https://api.weather.gov/gridpoints/BOX/%s/forecast";

    public WeatherService(RestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    public WeatherResponse apply(WeatherRequest weatherRequest) {
        String url =  String.format(WEATHER_API_URL, weatherRequest.latLon());
        System.out.println("Formatted Weather API URL:" + url);
        String weatherForecast = restClient.get().uri(url)
                .retrieve().onStatus(HttpStatusCode::is5xxServerError, (request, response) -> {
                    System.out.println("Failed to fetch weather forecast for: " + weatherRequest.latLon());
                }).body(String.class);

        return new WeatherResponse(weatherForecast);
    }
}
