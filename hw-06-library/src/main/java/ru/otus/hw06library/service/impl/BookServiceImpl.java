package ru.otus.hw06library.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class BookServiceImpl implements BookService {

    private final BookRepo bookRepo;
    private final AuthorRepo authorRepo;
    private final GenreRepo genreRepo;

    @Override
    @Transactional
    public Book save(String title, Long authorId, Long genreId) {
        Author author = authorRepo.getById(authorId);
        if (author == null) {
            log.error("Автор не существует!");
            return null;
        }
        Genre genre = genreRepo.getById(genreId);
        if (genre == null) {
            log.error("Жанр не существует!");
            return null;
        }

        Book book = new Book(title, author, genre);

        return bookRepo.saveOrUpdate(book);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        bookRepo.deleteById(id);
    }

    @Override
    public List<Book> getAll() {
        return bookRepo.getAll();
    }

    @Override
    public Book getById(Long id) {
        return bookRepo.getById(id);
    }

    @Override
    public Book getByTitle(String title) {
        return bookRepo.getByTitle(title);
    }

    @Override
    public List<Book> getByAuthor(String authorName) {
        return bookRepo.getByAuthor(authorName);
    }

    @Override
    public List<Book> getByGenre(String genreName) {
        return bookRepo.getByGenre(genreName);
    }
}