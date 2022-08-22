package ru.otus.hw09library.repo;

import org.springframework.data.repository.CrudRepository;
import ru.otus.hw09library.model.Author;

import java.util.List;

public interface AuthorRepo extends CrudRepository<Author, Long> {

    List<Author> findAll();
}