package ru.otus.hw07library.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.hw07library.model.Genre;

public interface GenreRepo extends JpaRepository<Genre, Long> {
}