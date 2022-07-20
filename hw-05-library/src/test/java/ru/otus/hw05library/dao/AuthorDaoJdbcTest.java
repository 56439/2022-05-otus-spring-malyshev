package ru.otus.hw05library.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.hw05library.model.Author;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.otus.hw05library.TestData.AUTHOR;
import static ru.otus.hw05library.TestData.NEW_AUTHOR;

@JdbcTest
@Import(AuthorDaoJdbc.class)
@DisplayName(value = "DAO для работы с авторами")
public class AuthorDaoJdbcTest {

    @Autowired
    AuthorDaoJdbc authorDao;

    @Test
    @DisplayName(value = "добавляет автора в базу")
    void insert() {
        int expected = authorDao.count() + 1;
        authorDao.insert(NEW_AUTHOR);
        assertEquals(expected, authorDao.count());
    }

    @Test
    @DisplayName(value = "достает из базы всех авторов")
    void getAll() {
        List<Author> authors = authorDao.getAll();
        assertEquals(authors.size(), authorDao.count());
    }

    @Test
    @DisplayName(value = "достает из базы автора по имени")
    void getByName() {
        Author author = authorDao.getByName(AUTHOR.getName());
        assertEquals(author.getId(), AUTHOR.getId());
    }
}