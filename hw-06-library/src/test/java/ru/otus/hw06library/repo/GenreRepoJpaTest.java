package ru.otus.hw06library.repo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.hw06library.model.Genre;
import ru.otus.hw06library.repo.impl.GenreRepoJpa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.otus.hw06library.TestData.GENRE;
import static ru.otus.hw06library.TestData.NEW_GENRE;

@DataJpaTest
@Import(GenreRepoJpa.class)
@DisplayName(value = "Репозиторий для работы с жанрами")
public class GenreRepoJpaTest {

    @Autowired
    GenreRepoJpa genreRepo;

    @Test
    @DisplayName(value = "добавляет жанр в базу")
    void insert() {
        Long expected = genreRepo.count() + 1;
        genreRepo.saveOrUpdate(NEW_GENRE);
        assertEquals(expected, genreRepo.count());
    }

    @Test
    @DisplayName(value = "достает из базы жанр по ID")
    void getByName() {
        Genre genre = genreRepo.getById(GENRE.getId());
        assertEquals(genre.getId(), GENRE.getId());
    }
}
