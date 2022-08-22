package ru.otus.hw09library.service.impl;

import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw09library.dto.BookDto;
import ru.otus.hw09library.dto.BookEditDto;
import ru.otus.hw09library.model.Author;
import ru.otus.hw09library.model.Book;
import ru.otus.hw09library.model.Comment;
import ru.otus.hw09library.model.Genre;
import ru.otus.hw09library.repo.AuthorRepo;
import ru.otus.hw09library.repo.BookRepo;
import ru.otus.hw09library.repo.GenreRepo;
import ru.otus.hw09library.service.BookService;

import java.util.List;

@Service
@RequiredArgsConstructor
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
    public void deleteById(Long id) {
        bookRepo.deleteById(id);
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
        Book book = bookRepo.findById(id).orElseThrow();
        List<Comment> comments = book.getComments();

        Hibernate.initialize(comments);
        book.setComments(comments);

        return BookDto.fromModelWithComments(book);
    }

    @Override
    public BookDto getByTitle(String title) {
        Book book = bookRepo.getByTitle(title);
        return BookDto.fromModel(book);
    }

    @Override
    public List<BookDto> getByAuthor(String authorName) {
        List<Book> books = bookRepo.getByAuthor_Name(authorName);
        return BookDto.fromModelsList(books);
    }

    @Override
    public List<BookDto> getByGenre(String genreName) {
        List<Book> books = bookRepo.getByGenre_Name(genreName);
        return BookDto.fromModelsList(books);
    }
}