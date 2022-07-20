package ru.otus.hw06library.repo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.hw06library.model.Comment;
import ru.otus.hw06library.repo.impl.CommentRepoJpa;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.otus.hw06library.TestData.BOOK;
import static ru.otus.hw06library.TestData.NEW_COMMENT;

@DataJpaTest
@Import(CommentRepoJpa.class)
@DisplayName(value = "Репозиторий для работы с комментариями")
public class CommentRepoJpaTest {

    @Autowired
    CommentRepoJpa commentRepo;

    @Test
    @DisplayName(value = "добавляет новый комментарий")
    void saveOrUpdate() {
        commentRepo.saveOrUpdate(NEW_COMMENT);

        assertEquals(commentRepo.getById(NEW_COMMENT.getId()), NEW_COMMENT);
    }

    @Test
    @DisplayName(value = "удаляет комментарий по ID")
    void deleteById() {
        Long expected = commentRepo.count() - 1;
        commentRepo.deleteById(1L);

        assertEquals(expected, commentRepo.count());
    }

    @Test
    @DisplayName(value = "достает все комментарии к книге по ее ID")
    void getAllByBookId() {
        Integer expected = 2;
        List<Comment> comments = commentRepo.getAllByBookId(BOOK.getId());

        assertEquals(expected, comments.size());
    }

    @Test
    @DisplayName(value = "достает комментарий по ID")
    void getById() {
        Long expected = 2L;
        Comment comment = commentRepo.getById(expected);

        assertEquals(expected, comment.getId());
    }
}