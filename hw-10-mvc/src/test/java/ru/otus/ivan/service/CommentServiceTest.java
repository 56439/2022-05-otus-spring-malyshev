package ru.otus.ivan.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.ivan.dto.CommentDto;
import ru.otus.ivan.service.impl.CommentServiceImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.otus.ivan.TestData.*;

@SpringBootTest
@DisplayName("Сервис для работы с комментариями")
public class CommentServiceTest {

    @Mock
    private CommentServiceImpl commentService;

    @Test
    @DisplayName("сохраняет комментарий")
    void save_Test() {
        Mockito.when(commentService.save(COMMENT_EDIT_DTO)).thenReturn(COMMENT_DTO);

        CommentDto comment = commentService.save(COMMENT_EDIT_DTO);

        assertEquals(COMMENT_DTO, comment);
        Mockito.verify(commentService).save(COMMENT_EDIT_DTO);
    }

    @Test
    @DisplayName("удаляет комментарий")
    void deleteById_Test() {
        boolean expected = true;
        Long commentId = 1L;
        Mockito.when(commentService.deleteById(commentId)).thenReturn(expected);

        boolean isDeleted = commentService.deleteById(1L);

        assertEquals(expected, isDeleted);
        Mockito.verify(commentService).deleteById(commentId);
    }

    @Test
    @DisplayName("получает все комментарии к книге по ее ID")
    void getAllByBookId_Test() {
        Long bookId = 1L;
        Mockito.when(commentService.getAllByBookId(bookId)).thenReturn(COMMENTS_DTO_LIST);

        List<CommentDto> comments = commentService.getAllByBookId(bookId);

        assertEquals(COMMENTS_DTO_LIST, comments);
        Mockito.verify(commentService).getAllByBookId(bookId);
    }
}
