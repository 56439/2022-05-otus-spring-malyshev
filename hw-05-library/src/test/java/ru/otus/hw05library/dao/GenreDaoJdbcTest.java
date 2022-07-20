package ru.otus.hw05library.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.hw05library.model.Genre;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.otus.hw05library.TestData.GENRE;
import static ru.otus.hw05library.TestData.NEW_GENRE;

@JdbcTest
@Import(GenreDaoJdbc.class)
@DisplayName(value = "DAO для работы с жанрами")
public class GenreDaoJdbcTest {

    @Autowired
    GenreDaoJdbc genreDao;

    @Test
    @DisplayName(value = "добавляет жанр в базу")
    void insert() {
        int expected = genreDao.count() + 1;
        genreDao.insert(NEW_GENRE);
        assertEquals(expected, genreDao.count());
    }

    @Test
    @DisplayName(value = "достает из базы все жанры")
    void getAll() {
        List<Genre> genres = genreDao.getAll();
        assertEquals(genres.size(), genreDao.count());
    }

    @Test
    @DisplayName(value = "достает из базы жанр по названию")
    void getByName() {
        Genre genre = genreDao.getByName(GENRE.getName());
        assertEquals(genre.getId(), GENRE.getId());
    }
}
