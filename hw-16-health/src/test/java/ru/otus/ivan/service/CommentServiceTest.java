package ru.otus.ivan.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.ivan.dto.CommentDto;
import ru.otus.ivan.repo.BookRepo;
import ru.otus.ivan.service.impl.CommentServiceImpl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.otus.ivan.TestData.BOOK;
import static ru.otus.ivan.TestData.COMMENT_EDIT_DTO;

@SpringBootTest
@DisplayName("Сервис для работы с комментариями")
public class CommentServiceTest {

    @Autowired
    private CommentServiceImpl commentService;

    @Mock
    private BookRepo bookRepo;

    @Test
    @DisplayName("сохраняет комментарий")
    void save_Test() {
        Long expected = 5L;
        Mockito.when(bookRepo.findById(COMMENT_EDIT_DTO.getBook())).thenReturn(Optional.of(BOOK));
        CommentDto comment = commentService.save(COMMENT_EDIT_DTO);

        assertEquals(expected, comment.getId());
    }

    @Test
    @DisplayName("удаляет комментарий")
    void deleteById_Test() {
        boolean expected = true;
        Long commentId = 1L;

        boolean isDeleted = commentService.deleteById(commentId);

        assertEquals(expected, isDeleted);
    }
}