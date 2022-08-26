package ru.otus.hw09library.repo;

import org.springframework.data.repository.CrudRepository;
import ru.otus.hw09library.model.Genre;

import java.util.List;

public interface GenreRepo extends CrudRepository<Genre, Long> {

    List<Genre> findAll();
}