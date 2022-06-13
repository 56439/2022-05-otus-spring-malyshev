package ru.otus.hw03studenttesting;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.otus.hw03studenttesting.service.ConsoleService;

@AllArgsConstructor
@SpringBootApplication
public class StudentTestingApplication implements CommandLineRunner {

    private final ConsoleService consoleService;

    public static void main(String[] args) {
        SpringApplication.run(StudentTestingApplication.class, args);
    }

    @Override
    public void run(String... args) {
        consoleService.process();
    }
}