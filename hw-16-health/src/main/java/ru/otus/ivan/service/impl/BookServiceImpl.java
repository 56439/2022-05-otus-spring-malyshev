package ru.otus.ivan.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.ivan.dto.BookDto;
import ru.otus.ivan.dto.BookEditDto;
import ru.otus.ivan.model.Author;
import ru.otus.ivan.model.Book;
import ru.otus.ivan.model.Comment;
import ru.otus.ivan.model.Genre;
import ru.otus.ivan.repo.AuthorRepo;
import ru.otus.ivan.repo.BookRepo;
import ru.otus.ivan.repo.GenreRepo;
import ru.otus.ivan.service.BookService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookServiceImpl implements BookService {

    private final BookRepo bookRepo;
    private final AuthorRepo authorRepo;
    private final GenreRepo genreRepo;

    @Override
    public BookDto save(BookEditDto book) {
        Author authorModel = authorRepo.findById(book.getAuthor()).orElseThrow();
        Genre genreModel = genreRepo.findById(book.getGenre()).orElseThrow();
        Book bookModel = new Book(book.getTitle(), authorModel, genreModel);

        return BookDto.fromModel(bookRepo.save(bookModel));
    }

    @Override
    public BookDto update(BookEditDto bookEditDto) {
        Book book = bookRepo.findById(bookEditDto.getId()).orElseThrow();
        Author author = authorRepo.findById(bookEditDto.getAuthor()).orElseThrow();
        Genre genre = genreRepo.findById(bookEditDto.getGenre()).orElseThrow();

        book.setTitle(bookEditDto.getTitle());
        book.setAuthor(author);
        book.setGenre(genre);

        return BookDto.fromModel(bookRepo.save(book));
    }

    @Override
    public boolean deleteById(Long id) {
        try {
            bookRepo.deleteById(id);
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookDto> getAll() {
        List<Book> books = bookRepo.findAll();
        return BookDto.fromModelsList(books);
    }

    @Override
    public BookDto getById(Long id) {
        Book book = bookRepo.findById(id).orElseThrow();
        return BookDto.fromModel(book);
    }

    @Override
    @Transactional(readOnly = true)
    public BookDto getByIdWithComments(Long id) {
        Optional<Book> book = bookRepo.findById(id);
        List<Comment> comments = book.orElseThrow().getComments();

        Hibernate.initialize(comments);
        book.orElseThrow().setComments(comments);

        return BookDto.fromModelWithComments(book.orElseThrow());
    }
}