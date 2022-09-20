package ru.otus.ivan.repo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.ivan.model.Book;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.otus.ivan.TestData.BOOK;
import static ru.otus.ivan.TestData.BOOKS_LIST;

@SpringBootTest
@DisplayName("Репозиторий для работы с книгами")
public class BookRepoTest {

    @Mock
    private BookRepo bookRepo;

    @Test
    @DisplayName("получает из БД книгу по названию")
    void getByTitle_Test() {
        String title = "book";
        Mockito.when(bookRepo.getByTitle(title)).thenReturn(BOOK);

        Book book = bookRepo.getByTitle(title);

        assertEquals(BOOK, book);
    }

    @Test
    @DisplayName("получает из БД список книг по имени автора")
    void getByAuthor_Name_Test() {
        String authorName = "author";
        Mockito.when(bookRepo.getByAuthor_Name(authorName)).thenReturn(BOOKS_LIST);

        List<Book> books = bookRepo.getByAuthor_Name(authorName);

        assertEquals(BOOKS_LIST, books);
    }

    @Test
    @DisplayName("получает из БД список книг по названию жанра")
    void getByGenre_Name_Test() {
        String genreName = "genre";
        Mockito.when(bookRepo.getByGenre_Name(genreName)).thenReturn(BOOKS_LIST);

        List<Book> books = bookRepo.getByGenre_Name(genreName);

        assertEquals(BOOKS_LIST, books);
    }
}