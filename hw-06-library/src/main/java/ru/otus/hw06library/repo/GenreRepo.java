package ru.otus.hw06library.repo;

import ru.otus.hw06library.model.Genre;

public interface GenreRepo {

    Genre saveOrUpdate(Genre genre);

    Long count();

    Genre getById(long id);
}