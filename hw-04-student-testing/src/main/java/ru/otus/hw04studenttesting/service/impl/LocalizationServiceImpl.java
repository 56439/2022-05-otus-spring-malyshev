package ru.otus.hw04studenttesting.service.impl;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.hw04studenttesting.config.AppConfiguration;
import ru.otus.hw04studenttesting.service.LocalizationService;

import java.util.List;
import java.util.Locale;

@Service
public class LocalizationServiceImpl implements LocalizationService {

    private final MessageSource messageSource;
    private final List<String> languages;
    private String selectedLanguage;

    public LocalizationServiceImpl(AppConfiguration configuration, MessageSource messageSource) {
        this.languages = configuration.getLanguages();
        this.messageSource = messageSource;
    }

    @Override
    public List<String> getLanguages() {
        return languages;
    }

    @Override
    public String getSelectedLanguage() {
        return selectedLanguage;
    }

    @Override
    public void setSelectedLanguage(String selectedLanguage) {
        this.selectedLanguage = selectedLanguage;
    }

    @Override
    public String getBundledMessage(String key) {
        return messageSource.getMessage(key, null, new Locale(selectedLanguage, selectedLanguage.toUpperCase()));
    }
}