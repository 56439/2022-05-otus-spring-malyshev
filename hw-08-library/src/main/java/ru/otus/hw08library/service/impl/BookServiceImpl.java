package ru.otus.hw08library.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.hw08library.model.Author;
import ru.otus.hw08library.model.Book;
import ru.otus.hw08library.model.Genre;
import ru.otus.hw08library.repo.AuthorRepo;
import ru.otus.hw08library.repo.BookRepo;
import ru.otus.hw08library.repo.GenreRepo;
import ru.otus.hw08library.service.BookService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepo bookRepo;
    private final AuthorRepo authorRepo;
    private final GenreRepo genreRepo;

    @Override
    public Book save(String title, String authorName, List<String> genresNames) {
        Author author = authorRepo.findByName(authorName);
        List<Genre> genres = new ArrayList<>();

        for (String genreName : genresNames) {
            Genre genre = genreRepo.findByName(genreName);
            genres.add(genre);
        }

        Book book = new Book(title, author, genres);

        return bookRepo.save(book);
    }

    @Override
    public Book update(Book book) {
        return bookRepo.save(book);
    }

    @Override
    public void delete(Book book) {
        bookRepo.delete(book);
    }

    @Override
    public List<Book> getAll() {
        return bookRepo.findAll();
    }

    @Override
    public Book getByTitle(String title) {
        return bookRepo.findByTitle(title);
    }

    @Override
    public List<Book> getByAuthor(String authorName) {
        Author author = authorRepo.findByName(authorName);
        return bookRepo.findByAuthor(author);
    }

    @Override
    public List<Book> getByGenre(String genreName) {
        Genre genre = genreRepo.findByName(genreName);
        return bookRepo.findByGenresContains(genre);
    }
}
