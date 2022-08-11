package ru.otus.hw07library.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw07library.model.Author;
import ru.otus.hw07library.model.Book;
import ru.otus.hw07library.model.Comment;
import ru.otus.hw07library.model.Genre;
import ru.otus.hw07library.repo.AuthorRepo;
import ru.otus.hw07library.repo.BookRepo;
import ru.otus.hw07library.repo.GenreRepo;
import ru.otus.hw07library.service.BookService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookServiceImpl implements BookService {

    private final BookRepo bookRepo;
    private final AuthorRepo authorRepo;
    private final GenreRepo genreRepo;

    @Override
    public Book save(String title, Long authorId, Long genreId) {
        Author author = authorRepo.findById(authorId).orElseThrow();
        Genre genre = genreRepo.findById(genreId).orElseThrow();
        Book book = new Book(title, author, genre);

        return bookRepo.save(book);
    }

    @Override
    public void deleteById(Long id) {
        bookRepo.deleteById(id);
    }

    @Override
    public List<Book> getAll() {
        return bookRepo.findAll();
    }

    @Override
    public Book getById(Long id) {
        return bookRepo.findById(id).orElseThrow();
    }

    @Override
    @Transactional(readOnly = true)
    public Book getByIdWithComments(Long id) {
        Book book = bookRepo.findById(id).orElseThrow();
        List<Comment> comments = book.getComments();

        Hibernate.initialize(comments);
        book.setComments(comments);

        return book;
    }

    @Override
    public Book getByTitle(String title) {
        return bookRepo.getByTitle(title);
    }

    @Override
    public List<Book> getByAuthor(String authorName) {
        return bookRepo.getByAuthor_Name(authorName);
    }

    @Override
    public List<Book> getByGenre(String genreName) {
        return bookRepo.getByGenre_Name(genreName);
    }
}