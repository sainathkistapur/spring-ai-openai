package dev.springai.openai.tool.calling.config;

import dev.springai.openai.tool.calling.tools.weather.domain.WeatherRequest;
import dev.springai.openai.tool.calling.tools.weather.domain.WeatherResponse;
import dev.springai.openai.tool.calling.tools.weather.function.WeatherService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.web.client.RestClient;

import java.util.function.Function;

@Configuration(proxyBeanMethods = false)
public class WeatherTooConfig {

    public static final String WEATHER_FORECAST = "weatherForecast";

    @Bean
    RestClient restClient() {
        return RestClient.builder().build();
    }

    @Bean(WEATHER_FORECAST)
    @Description("Get the weather forecast at location")
    Function<WeatherRequest, WeatherResponse> weatherForecast() {
        return weatherService(restClient());
    }

    @Bean
    WeatherService weatherService(RestClient restClient) {
        return new WeatherService(restClient);
    }

}
