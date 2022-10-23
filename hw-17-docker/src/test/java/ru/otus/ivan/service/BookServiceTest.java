package ru.otus.ivan.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import ru.otus.ivan.dto.BookDto;
import ru.otus.ivan.repo.BookRepo;
import ru.otus.ivan.service.impl.BookServiceImpl;

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
    @DisplayName("обновляет книгу")
    @WithMockUser(username = "admin", roles = "ADMIN")
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
}