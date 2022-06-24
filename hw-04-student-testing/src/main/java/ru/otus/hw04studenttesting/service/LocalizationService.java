package ru.otus.hw04studenttesting.service;

import java.util.List;

public interface LocalizationService {

    List<String> getLanguages();

    void setSelectedLanguage(String selectedLanguage);

    String getSelectedLanguage();

    String getBundledMessage(String key);
}