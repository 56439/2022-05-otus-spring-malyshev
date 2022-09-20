package ru.otus.ivan.repo;

import org.springframework.data.repository.CrudRepository;
import ru.otus.ivan.model.Genre;

import java.util.List;

public interface GenreRepo extends CrudRepository<Genre, Long> {

    List<Genre> findAll();
}