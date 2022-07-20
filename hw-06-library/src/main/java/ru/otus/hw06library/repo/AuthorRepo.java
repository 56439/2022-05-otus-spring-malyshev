package ru.otus.hw06library.repo;

import ru.otus.hw06library.model.Author;

import java.util.List;

public interface AuthorRepo {

    Author saveOrUpdate(Author author);

    List<Author> getAll();

    Author getByName(String name);

    Long count();
}