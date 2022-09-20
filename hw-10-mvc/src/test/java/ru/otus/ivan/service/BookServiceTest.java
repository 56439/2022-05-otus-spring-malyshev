package ru.otus.ivan.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.ivan.dto.BookDto;
import ru.otus.ivan.service.impl.BookServiceImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.otus.ivan.TestData.*;

@SpringBootTest
@DisplayName("Сервис для работы с книгами")
public class BookServiceTest {

    @Mock
    private BookServiceImpl bookService;

    @Test
    @DisplayName("сохраняет книгу")
    void save_Test() {
        Mockito.when(bookService.save(BOOK_EDIT_DTO)).thenReturn(BOOK_DTO);

        BookDto book = bookService.save(BOOK_EDIT_DTO);

        assertEquals(BOOK_DTO, book);
        Mockito.verify(bookService).save(BOOK_EDIT_DTO);
    }

    @Test
    @DisplayName("обновляет книгу")
    void update_Test() {
        Mockito.when(bookService.update(BOOK_EDIT_DTO)).thenReturn(BOOK_DTO);

        BookDto book = bookService.update(BOOK_EDIT_DTO);

        assertEquals(BOOK_DTO, book);
        Mockito.verify(bookService).update(BOOK_EDIT_DTO);
    }

    @Test
    @DisplayName("удаляет книгу")
    void delete_Test() {
        boolean expected = true;
        Long bookId = 1L;
        Mockito.when(bookService.deleteById(bookId)).thenReturn(expected);

        boolean isDeleted = bookService.deleteById(1L);

        assertEquals(expected, isDeleted);
        Mockito.verify(bookService).deleteById(bookId);
    }

    @Test
    @DisplayName("получает книгу по ID")
    void getById_Test() {
        Long bookId = 1L;
        Mockito.when(bookService.getById(bookId)).thenReturn(BOOK_DTO);

        BookDto bookDto = bookService.getById(bookId);

        assertEquals(BOOK_DTO, bookDto);
        Mockito.verify(bookService).getById(bookId);
    }

    @Test
    @DisplayName("получает книгу по названию")
    void getByTitle_Test() {
        String title = "book";
        Mockito.when(bookService.getByTitle(title)).thenReturn(BOOK_DTO);

        BookDto book = bookService.getByTitle(title);

        assertEquals(BOOK_DTO, book);
        Mockito.verify(bookService).getByTitle(title);
    }

    @Test
    @DisplayName("получает книгу по имени автора")
    void getByAuthorName_Test() {
        String authorName = "author";
        Mockito.when(bookService.getByAuthorName(authorName)).thenReturn(BOOKS_DTO_LIST);

        List<BookDto> books = bookService.getByAuthorName(authorName);

        assertEquals(BOOKS_DTO_LIST, books);
        Mockito.verify(bookService).getByAuthorName(authorName);
    }

    @Test
    @DisplayName("получает книгу по названию жанра")
    void getByGenreName_Test() {
        String genreName = "genre";
        Mockito.when(bookService.getByGenreName(genreName)).thenReturn(BOOKS_DTO_LIST);

        List<BookDto> books = bookService.getByGenreName(genreName);

        assertEquals(BOOKS_DTO_LIST, books);
        Mockito.verify(bookService).getByGenreName(genreName);
    }
}