package ru.otus.hw06library.repo;

import ru.otus.hw06library.model.Author;

public interface AuthorRepo {

    Author saveOrUpdate(Author author);

    Long count();

    Author getById(long id);
}