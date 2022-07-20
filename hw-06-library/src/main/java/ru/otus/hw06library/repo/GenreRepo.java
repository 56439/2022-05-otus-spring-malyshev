package ru.otus.hw06library.repo;

import ru.otus.hw06library.model.Genre;

import java.util.List;

public interface GenreRepo {

    Genre saveOrUpdate(Genre genre);

    List<Genre> getAll();

    Genre getByName(String name);

    Long count();
}