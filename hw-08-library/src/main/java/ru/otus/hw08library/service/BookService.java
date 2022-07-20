package ru.otus.hw08library.service;

import ru.otus.hw08library.model.Book;

import java.util.List;

public interface BookService {

    Book save(String title, String authorName, List<String> genres);

    Book update(Book book);

    void delete(Book book);

    List<Book> getAll();

    Book getByTitle(String title);

    List<Book> getByAuthor(String authorName);

    List<Book> getByGenre(String genreName);
}
