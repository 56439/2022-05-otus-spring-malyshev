package ru.otus.hw03studenttesting.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.hw03studenttesting.config.AppConfiguration;
import ru.otus.hw03studenttesting.config.TestConfig;
import ru.otus.hw03studenttesting.service.impl.IOServiceImpl;
import ru.otus.hw03studenttesting.service.impl.LocalizationServiceImpl;

import java.util.List;

@SpringBootTest(classes = {
        IOServiceImpl.class,
        LocalizationServiceImpl.class,
        AppConfiguration.class,
        TestConfig.class
})
@DisplayName(value = "Класс IOService")
public class IOServiceImplTest {

    @Autowired
    IOServiceImpl ioService;

    @Autowired
    LocalizationServiceImpl localizationService;

    @BeforeEach
    void init() {
        localizationService.setSelectedLanguage("en");
    }

    @Test
    @DisplayName(value = "Вывод сообщения")
    void print() {
        String testMessage = "TEST_MESSAGE";
        ioService.print(testMessage);
    }

    @Test
    @DisplayName(value = "Вывод BundledMessage")
    void printBundledMessage() {
        String testMessage = "enter.name";
        ioService.printBundledMessage(testMessage);
    }

    @Test
    @DisplayName(value = "Вывод списка")
    void printList() {
        List<String> list = List.of("element1", "element2", "element3");
        ioService.printList(list);
    }
}