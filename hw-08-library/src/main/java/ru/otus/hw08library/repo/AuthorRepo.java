package ru.otus.hw08library.repo;

import org.springframework.data.repository.CrudRepository;
import ru.otus.hw08library.model.Author;

public interface AuthorRepo extends CrudRepository<Author, Integer> {

    Author findByName(String name);
}
