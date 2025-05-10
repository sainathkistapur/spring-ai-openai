package dev.springai.openai.tool.calling.tools.weather.domain;

import org.springframework.ai.tool.annotation.ToolParam;

public record WeatherRequest(@ToolParam(description = "The latitude and longitude of the location") String latLon) {}