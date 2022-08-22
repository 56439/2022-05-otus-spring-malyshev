package ru.otus.hw09library.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.hw09library.dto.BookDto;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static ru.otus.hw09library.TestData.*;

@SpringBootTest
@DisplayName(value = "Сервис для работы с книгами")
public class BookServiceTest {

    @Autowired
    BookService bookService;

    @Test
    @DisplayName("Сохраняет книгу в базу")
    void save() {
        BookDto bookDto = bookService.save(ADD_BOOK_EDIT_DTO);
        assertEquals(bookDto.getTitle(), ADD_BOOK_EDIT_DTO.getTitle());
    }

    @Test
    @DisplayName("обновляет данные книги в базе")
    void update() {
        BookDto bookDto = bookService.update(UPDATE_BOOK_EDIT_DTO);
        assertEquals(bookDto.getTitle(), UPDATE_BOOK_EDIT_DTO.getTitle());
    }

    @Test
    @DisplayName("достает из базы книгу по ID")
    void getById() {
        BookDto bookDto = bookService.getById(BOOK_DTO.getId());

        assertEquals(BOOK_DTO.getId(), bookDto.getId());
    }

    @Test
    @DisplayName("достает из базы книгу по ID с комментариями")
    void getByIdWithComments() {
        BookDto bookDto = bookService.getByIdWithComments(BOOK_DTO.getId());

        assertEquals(BOOK_DTO.getId(), bookDto.getId());
        assertNotEquals(0, bookDto.getComments().size());
    }

    @Test
    @DisplayName(value = "достает из базы книгу по названию")
    void getByTitle() {
        String title = "book";
        BookDto bookDto = bookService.getByTitle(title);

        assertEquals(title, bookDto.getTitle());
    }

    @Test
    @DisplayName("достает из базы книгу по автору")
    void getByAuthor() {
        String name = AUTHOR_DTO.getName();
        List<BookDto> bookDtos = bookService.getByAuthor(name);

        assertEquals(1, bookDtos.size());
    }

    @Test
    @DisplayName("достает из базы книгу по жанру")
    void getByGenre() {
        String name = GENRE_DTO.getName();
        List<BookDto> bookDtos = bookService.getByGenre(name);

        assertEquals(3, bookDtos.size());
    }
}