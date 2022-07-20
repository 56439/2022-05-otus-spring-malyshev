package ru.otus.hw05library.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.hw05library.model.Book;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.otus.hw05library.TestData.*;

@JdbcTest
@Import(BookDaoJdbc.class)
@DisplayName(value = "DAO для работы с книгами")
public class BookDaoJdbcTest {

    @Autowired
    BookDaoJdbc bookDao;

    @Test
    @DisplayName(value = "добавляет книгу в базу")
    void insert() {
        int expected = bookDao.count() + 1;
        bookDao.insert(NEW_BOOK_1);
        assertEquals(expected, bookDao.count());
    }

    @Test
    @DisplayName(value = "удаляет книгу по ID")
    void deleteById() {
        int expected = bookDao.count() - 1;
        bookDao.deleteById(1);
        assertEquals(expected, bookDao.count());
    }

    @Test
    @DisplayName(value = "достает из базы все книги")
    void getAll() {
        List<Book> allBooks = bookDao.getAll();
        assertEquals(allBooks.size(), bookDao.count());
    }

    @Test
    @DisplayName(value = "достает из базы книгу по ID")
    void getById() {
        int id = 1;
        Book book = bookDao.getById(id);
        assertEquals(BOOK, book);
    }

    @Test
    @DisplayName(value = "достает из базы книгу по названию")
    void getByName() {
        String name = "book";
        Book book = bookDao.getByName(name);
        assertEquals(BOOK, book);
    }

    @Test
    @DisplayName(value = "достает из базы список книг по автору")
    void getByAuthor() {
        List<Book> books = bookDao.getByAuthor(AUTHOR.getName());

        assertEquals(AUTHOR_BOOKS_COUNT, books.size());
    }

    @Test
    @DisplayName(value = "достает из базы список книг по жанру")
    void getByGenre() {
        List<Book> books = bookDao.getByGenre(GENRE.getName());

        assertEquals(GENRE_BOOKS_COUNT, books.size());
    }
}
