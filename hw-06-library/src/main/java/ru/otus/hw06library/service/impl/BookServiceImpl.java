package ru.otus.hw06library.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw06library.model.Author;
import ru.otus.hw06library.model.Book;
import ru.otus.hw06library.model.Genre;
import ru.otus.hw06library.repo.AuthorRepo;
import ru.otus.hw06library.repo.BookRepo;
import ru.otus.hw06library.repo.GenreRepo;
import ru.otus.hw06library.service.BookService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepo bookRepo;
    private final AuthorRepo authorService;
    private final GenreRepo genreService;

    @Override
    public Book create(String title, String authorName, String genreName) {
        Book book = bookRepo.getByTitle(title);
        if (book != null) {
            return null;
        }

        Author author = authorService.getByName(authorName);
        if (author == null) {
            author = new Author(authorName);
        }

        Genre genre = genreService.getByName(genreName);
        if (genre == null) {
            genre = new Genre(genreName);
        }

        return new Book(title, author, genre);
    }

    @Override
    @Transactional
    public Book save(Book book) {
        return bookRepo.saveOrUpdate(book);
    }

    @Override
    @Transactional
    public boolean deleteById(Long id) {
        return bookRepo.deleteById(id);
    }

    @Override
    @Transactional
    public List<Book> getAll() {
        return bookRepo.getAll();
    }

    @Override
    @Transactional
    public Book getById(Long id) {
        return bookRepo.getById(id);
    }

    @Override
    @Transactional
    public Book getByTitle(String title) {
        return bookRepo.getByTitle(title);
    }

    @Override
    @Transactional
    public List<Book> getByAuthor(String authorName) {
        return bookRepo.getByAuthor(authorName);
    }

    @Override
    @Transactional
    public List<Book> getByGenre(String genreName) {
        return bookRepo.getByGenre(genreName);
    }
}