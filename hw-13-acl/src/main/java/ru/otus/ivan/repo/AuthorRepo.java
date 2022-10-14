package ru.otus.ivan.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.ivan.model.Author;

public interface AuthorRepo extends JpaRepository<Author, Long> {
}