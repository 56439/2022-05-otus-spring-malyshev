package ru.otus.hw04studenttesting;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.otus.hw04studenttesting.service.ConsoleService;

@SpringBootApplication
@RequiredArgsConstructor
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