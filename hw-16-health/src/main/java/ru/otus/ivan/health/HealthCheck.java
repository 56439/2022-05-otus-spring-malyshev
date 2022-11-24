package ru.otus.ivan.health;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;
import ru.otus.ivan.repo.BookRepo;

@Component
@RequiredArgsConstructor
@Slf4j
public class HealthCheck implements HealthIndicator {

    private final BookRepo bookRepo;

    @Override
    public Health health() {
        try {
            bookRepo.findAll();
            return Health.up().build();
        } catch (Exception e) {
            log.error("Сервер не отвечает: " + e.getMessage());
            return Health.down()
                    .status(Status.DOWN)
                    .withDetail("message", "Что-то пошло не так! Сервер не отвечает!")
                    .build();
        }
    }
}