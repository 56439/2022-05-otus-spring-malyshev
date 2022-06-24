package ru.otus.hw03studenttesting.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@Getter
@Setter
@ConfigurationProperties("app.localization")
public class AppConfiguration {

    private String selectLanguageMessage;
    private String selectedLanguageNotExistMessage;

    private Map<String, String> questionsResources;
    private List<String> languages;
}