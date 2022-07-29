package ru.otus.hw06library.repo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.hw06library.model.Book;
import ru.otus.hw06library.repo.impl.BookRepoJpa;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.otus.hw06library.TestData.*;

@DataJpaTest
@Import(BookRepoJpa.class)
@DisplayName(value = "Репозиторий для работы с книгами")
public class BookRepoJpaTest {

    @Autowired
    BookRepoJpa bookRepo;

    @Test
    @DisplayName(value = "добавляет книгу в базу")
    void saveOrUpdate() {
        Long expected = bookRepo.count() + 1;
        bookRepo.saveOrUpdate(NEW_BOOK);

        assertEquals(expected, bookRepo.count());
    }

    @Test
    @DisplayName(value = "достает из базы все книги")
    void getAll() {
        List<Book> allBooks = bookRepo.getAll();

        assertEquals(allBooks.size(), bookRepo.count());
    }

    @Test
    @DisplayName(value = "достает из базы книгу по ID")
    void getById() {
        Long id = 1L;
        Book book = bookRepo.getById(id);

        assertEquals(BOOK.getId(), book.getId());
    }

    @Test
    @DisplayName(value = "достает из базы книгу по названию")
    void getByName() {
        String title = "book";
        Book book = bookRepo.getByTitle(title);

        assertEquals(BOOK.getId(), book.getId());
    }

    @Test
    @DisplayName(value = "достает из базы список книг по автору")
    void getByAuthor() {
        List<Book> books = bookRepo.getByAuthor(AUTHOR.getName());

        assertEquals(AUTHOR_BOOKS_COUNT, books.size());
    }

    @Test
    @DisplayName(value = "достает из базы список книг по жанру")
    void getByGenre() {
        List<Book> books = bookRepo.getByGenre(GENRE.getName());

        assertEquals(GENRE_BOOKS_COUNT, books.size());
    }
}