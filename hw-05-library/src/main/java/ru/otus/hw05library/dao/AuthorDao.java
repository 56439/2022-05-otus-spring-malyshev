package ru.otus.hw05library.dao;

import ru.otus.hw05library.model.Author;

import java.util.List;

public interface AuthorDao {

    Author insert(Author author);

    List<Author> getAll();

    Author getByName(String name);

    Integer count();
}