package ru.otus.hw04studenttesting.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import ru.otus.hw04studenttesting.config.AppConfiguration;
import ru.otus.hw04studenttesting.model.User;
import ru.otus.hw04studenttesting.service.impl.ConsoleServiceImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = {ConsoleServiceImpl.class, AppConfiguration.class})
@ActiveProfiles("test")
@DisplayName(value = "Класс ConsoleServiceImpl")
public class ConsoleServiceTest {

    @Autowired
    AppConfiguration configuration;
    @SpyBean
    ConsoleServiceImpl consoleService;
    @MockBean
    IOService ioService;
    @MockBean
    LocalizationService localizationService;
    @MockBean
    QuizService quizService;

    @Test
    @DisplayName(value = "Выбор языка")
    void selectLanguage() {
        String expectedLanguage = "en";
        Mockito.when(localizationService.getLanguages()).thenReturn(List.of("ру", "en"));
        assertEquals(expectedLanguage, consoleService.selectLanguage("en"));
    }

    @Test
    @DisplayName(value = "Авторизация")
    void login() {
        Mockito.when(localizationService.getBundledMessage("authorized-as")).thenReturn("any text");
        String name = "Иван";
        String surname = "Малышев";
        User user = new User(name, surname);

        assertEquals(user, consoleService.login(name, surname));
    }
}