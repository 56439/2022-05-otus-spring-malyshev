package ru.otus.hw07library.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.hw07library.model.Author;

public interface AuthorRepo extends JpaRepository<Author, Long> {
}