package ru.otus.hw03studenttesting.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.hw03studenttesting.config.AppConfiguration;
import ru.otus.hw03studenttesting.service.impl.LocalizationServiceImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {LocalizationServiceImpl.class, AppConfiguration.class})
@DisplayName(value = "Класс LocalizationService")
public class LocalizationServiceImplTest {

    @Autowired
    LocalizationServiceImpl localizationService;

    @BeforeEach
    void init() {
        localizationService.setSelectedLanguage("en");
    }

    @Test
    @DisplayName(value = "Получение всех языков")
    public void getLanguages() {
        List<String> languages = localizationService.getLanguages();

        assertNotNull(languages);
    }

    @Test
    @DisplayName(value = "Получение выбранного языка")
    public void getSelectedLanguage() {
        String selectedLange = localizationService.getSelectedLanguage();

        assertEquals("en", selectedLange);
    }

    @Test
    @DisplayName(value = "Выбор языка")
    public void setSelectedLanguage() {
        localizationService.setSelectedLanguage("ru");
        String selectedLang = localizationService.getSelectedLanguage();

        assertEquals("ru", selectedLang);
    }

    @Test
    @DisplayName(value = "Получение BundledMessage")
    public void getBundledMessage() {
        String bundledMessage = localizationService.getBundledMessage("enter.name");

        assertEquals("Enter your name:", bundledMessage);
    }
}
