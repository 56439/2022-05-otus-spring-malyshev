package ru.otus.hw06library.service;

import ru.otus.hw06library.model.Book;

import java.util.List;

public interface BookService {

    Book create(String title, String authorName, String genreName);

    Book save(Book book);

    boolean deleteById(Long id);

    List<Book> getAll();

    Book getById(Long id);

    Book getByTitle(String title);

    List<Book> getByAuthor(String authorName);

    List<Book> getByGenre(String genreName);
}