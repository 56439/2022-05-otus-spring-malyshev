package ru.otus.ivan.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.ivan.model.Genre;

public interface GenreRepo extends JpaRepository<Genre, Long> {
}