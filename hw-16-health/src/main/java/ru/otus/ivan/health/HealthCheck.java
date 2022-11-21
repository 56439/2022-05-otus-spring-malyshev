package ru.otus.ivan.health;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;
import ru.otus.ivan.repo.BookRepo;

@Component
@RequiredArgsConstructor
public class HealthCheck implements HealthIndicator {

    private final BookRepo bookRepo;

    @Override
    public Health health() {
        if (bookRepo.findAll().isEmpty()) {
            return Health.down()
                    .status(Status.DOWN)
                    .withDetail("message", "Что-то пошло не так! Книги не найдены!")
                    .build();
        } else {
            return Health.up().build();
        }
    }
}