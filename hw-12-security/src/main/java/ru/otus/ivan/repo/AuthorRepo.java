package ru.otus.ivan.repo;

import org.springframework.data.repository.CrudRepository;
import ru.otus.ivan.model.Author;

import java.util.List;

public interface AuthorRepo extends CrudRepository<Author, Long> {

    List<Author> findAll();
}