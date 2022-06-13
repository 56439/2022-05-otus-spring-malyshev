package ru.otus.hw03studenttesting.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.hw03studenttesting.service.impl.IOServiceImpl;

@SpringBootTest(classes = {IOServiceImpl.class})
@DisplayName(value = "Класс IOService")
public class IOServiceImplTest {

    @Autowired
    IOServiceImpl ioService;

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
}