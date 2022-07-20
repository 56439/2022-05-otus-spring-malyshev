package ru.otus.hw05library.dao;

import ru.otus.hw05library.model.Genre;

import java.util.List;

public interface GenreDao {

    Genre insert(Genre genre);

    List<Genre> getAll();

    Genre getByName(String name);

    Integer count();
}