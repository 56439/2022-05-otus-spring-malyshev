package ru.otus.ivan.service;

import ru.otus.ivan.dto.BookDto;
import ru.otus.ivan.dto.BookEditDto;

import java.util.List;

public interface BookService {

    BookDto save(BookEditDto book);

    BookDto update(BookEditDto book);

    boolean deleteById(Long id);

    List<BookDto> getAll();

    BookDto getById(Long id);

    BookDto getByIdWithComments(Long id);
}