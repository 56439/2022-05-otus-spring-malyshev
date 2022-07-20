package ru.otus.hw05library.service;

import ru.otus.hw05library.model.Book;

import java.util.List;

public interface BookService {

    Book save(Book book);

    boolean deleteById(int id);

    List<Book> getAll();

    Book getById(int id);

    Book getByName(String name);

    List<Book> getByAuthor(String author);

    List<Book> getByGenre(String genre);
}