package ru.otus.hw05library.dao;

import ru.otus.hw05library.model.Book;

import java.util.List;

public interface BookDao {

    Book insert(Book book);

    boolean deleteById(int id);

    List<Book> getAll();

    Book getById(int id);

    Book getByName(String name);

    List<Book> getByAuthor(String author);

    List<Book> getByGenre(String genre);

    Integer count();
}