package ru.otus.hw06library.repo;

import ru.otus.hw06library.model.Book;

import java.util.List;

public interface BookRepo {

    Book saveOrUpdate(Book book);

    void deleteById(Long id);

    List<Book> getAll();

    Book getById(Long id, String entityGraph);

    Book getById(Long id);

    Book getByTitle(String title);

    List<Book> getByAuthor(String author);

    List<Book> getByGenre(String genre);

    Long count();
}