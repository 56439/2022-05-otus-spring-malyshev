package ru.otus.ivan.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.ivan.dto.BookDto;
import ru.otus.ivan.repo.BookRepo;
import ru.otus.ivan.service.impl.BookServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.otus.ivan.TestData.*;

@SpringBootTest
@DisplayName("Сервис для работы с книгами")
public class BookServiceTest {

    @Autowired
    private BookServiceImpl bookService;

    @Mock
    private BookRepo bookRepo;

    @Test
    @DisplayName("сохраняет книгу")
    void save_Test() {
        Long expected = 13L;
        BookDto book = bookService.save(BOOK_EDIT_DTO);

        assertEquals(expected, book.getId());
    }

    @Test
    @DisplayName("обновляет книгу")
    void update_Test() {
        BookDto book = bookService.update(BOOK_EDIT_DTO);

        assertEquals(BOOK_DTO.getId(), book.getId());
    }

    @Test
    @DisplayName("получает книгу по ID")
    void getById_Test() {
        Long bookId = 1L;
        Mockito.when(bookRepo.findById(bookId)).thenReturn(Optional.of(BOOK));

        BookDto bookDto = bookService.getById(bookId);

        assertEquals(BOOK_DTO.getId(), bookDto.getId());
    }

    @Test
    @DisplayName("удаляет книгу")
    void delete_Test() {
        boolean expected = true;
        Long bookId = 3L;

        boolean isDeleted = bookService.deleteById(bookId);

        assertEquals(expected, isDeleted);
    }

    @Test
    @DisplayName("получает книгу по названию")
    void getByTitle_Test() {
        String title = "book";
        Mockito.when(bookRepo.getByTitle(title)).thenReturn(BOOK);

        BookDto book = bookService.getByTitle(title);

        assertEquals(BOOK_DTO.getId(), book.getId());
    }

    @Test
    @DisplayName("получает список книг по имени автора")
    void getByAuthorName_Test() {
        Integer expected = 2;
        String authorName = "author";
        Mockito.when(bookRepo.getByAuthor_Name(authorName)).thenReturn(BOOKS_LIST);

        List<BookDto> books = bookService.getByAuthorName(authorName);

        assertEquals(expected, books.size());
    }

    @Test
    @DisplayName("получает список книг по названию жанра")
    void getByGenreName_Test() {
        Integer expected = 1;
        String genreName = "genre";
        Mockito.when(bookRepo.getByGenre_Name(genreName)).thenReturn(BOOKS_LIST);

        List<BookDto> books = bookService.getByGenreName(genreName);

        assertEquals(expected, books.size());
    }
}