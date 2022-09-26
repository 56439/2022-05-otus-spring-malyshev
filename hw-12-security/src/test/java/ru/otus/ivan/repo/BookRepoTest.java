package ru.otus.ivan.repo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.ivan.model.Book;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@DisplayName("Репозиторий для работы с книгами")
public class BookRepoTest {

    @Autowired
    private BookRepo bookRepo;

    @Test
    @DisplayName("получает из БД книгу по названию")
    void getByTitle_Test() {
        String title = "book";

        Book book = bookRepo.getByTitle(title);

        assertEquals(title, book.getTitle());
    }

    @Test
    @DisplayName("получает из БД список книг по имени автора")
    void getByAuthor_Name_Test() {
        String authorName = "author";

        List<Book> books = bookRepo.getByAuthor_Name(authorName);

        for (Book book : books) {
            assertEquals(authorName, book.getAuthor().getName());
        }
    }

    @Test
    @DisplayName("получает из БД список книг по названию жанра")
    void getByGenre_Name_Test() {
        String genreName = "genre";

        List<Book> books = bookRepo.getByGenre_Name(genreName);

        for (Book book : books) {
            assertEquals(genreName, book.getGenre().getName());
        }
    }
}