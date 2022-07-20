package ru.otus.hw08library.repo;

import org.springframework.data.repository.CrudRepository;
import ru.otus.hw08library.model.Genre;

public interface GenreRepo extends CrudRepository<Genre, Integer> {

    Genre findByName(String name);
}
