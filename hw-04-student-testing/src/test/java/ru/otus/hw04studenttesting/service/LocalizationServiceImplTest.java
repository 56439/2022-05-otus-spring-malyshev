package ru.otus.hw04studenttesting.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import ru.otus.hw04studenttesting.config.AppConfiguration;
import ru.otus.hw04studenttesting.config.TestConfig;
import ru.otus.hw04studenttesting.service.impl.LocalizationServiceImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = {LocalizationServiceImpl.class, AppConfiguration.class, TestConfig.class})
@ActiveProfiles("test")
@DisplayName(value = "Класс LocalizationService")
public class LocalizationServiceImplTest {

    @Autowired
    LocalizationServiceImpl localizationService;

    @MockBean
    AppConfiguration configuration;

    @BeforeEach
    void init() {
        localizationService.setSelectedLanguage("en");
    }

    @Test
    @DisplayName(value = "Получение всех языков")
    public void getLanguages() {
        Mockito.when(configuration.getLanguages()).thenReturn(List.of("ру", "en"));
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