package ru.otus.hw07library.service;

import ru.otus.hw07library.model.Book;

import java.util.List;

public interface BookService {

    Book save(String title, Long authorId, Long genreId);

    void deleteById(Long id);

    List<Book> getAll();

    Book getById(Long id);

    Book getByIdWithComments(Long id);

    Book getByTitle(String title);

    List<Book> getByAuthor(String authorName);

    List<Book> getByGenre(String genreName);
}