package ru.otus.hw07library.repo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.otus.hw07library.model.Book;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.otus.hw07library.TestData.*;

@DataJpaTest
@DisplayName(value = "Репозиторий для работы с книгами")
public class BookRepoTest {

    @Autowired
    BookRepo bookRepo;

    @Test
    @DisplayName(value = "достает из базы книгу по названию")
    void getByTitle() {
        String title = "book";
        Book book = bookRepo.getByTitle(title);

        assertEquals(title, book.getTitle());
    }

    @Test
    @DisplayName(value = "достает из базы список книг по автору")
    void getByAuthor_Name() {
        List<Book> books = bookRepo.getByAuthor_Name(AUTHOR.getName());

        assertEquals(AUTHOR_BOOKS_COUNT, books.size());
    }

    @Test
    @DisplayName(value = "достает из базы список книг по жанру")
    void getByGenre_Name() {
        List<Book> books = bookRepo.getByGenre_Name(GENRE.getName());

        assertEquals(GENRE_BOOKS_COUNT, books.size());
    }
}