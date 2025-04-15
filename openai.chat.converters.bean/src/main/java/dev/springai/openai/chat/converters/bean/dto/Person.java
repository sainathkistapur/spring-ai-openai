package dev.springai.openai.chat.converters.bean.dto;

import java.util.List;

public record Person(String name, List<String> achievements) {
}
