package ru.otus.hw09library.service;

import ru.otus.hw09library.dto.BookDto;
import ru.otus.hw09library.dto.BookEditDto;

import java.util.List;

public interface BookService {

    BookDto save(BookEditDto book);

    BookDto update(BookEditDto book);

    void deleteById(Long id);

    List<BookDto> getAll();

    BookDto getById(Long id);

    BookDto getByIdWithComments(Long id);

    BookDto getByTitle(String title);

    List<BookDto> getByAuthor(String authorName);

    List<BookDto> getByGenre(String genreName);
}