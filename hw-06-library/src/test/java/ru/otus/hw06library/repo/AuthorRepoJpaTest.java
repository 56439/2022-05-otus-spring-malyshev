package ru.otus.hw06library.repo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.hw06library.model.Author;
import ru.otus.hw06library.repo.impl.AuthorRepoJpa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.otus.hw06library.TestData.AUTHOR;
import static ru.otus.hw06library.TestData.NEW_AUTHOR;

@DataJpaTest
@Import(AuthorRepoJpa.class)
@DisplayName(value = "Репозиторий для работы с авторами")
public class AuthorRepoJpaTest {

    @Autowired
    AuthorRepoJpa authorRepo;

    @Test
    @DisplayName(value = "добавляет автора в базу")
    void insert() {
        Long expected = authorRepo.count() + 1;
        authorRepo.saveOrUpdate(NEW_AUTHOR);
        assertEquals(expected, authorRepo.count());
    }

    @Test
    @DisplayName(value = "достает из базы автора по ID")
    void getByName() {
        Author author = authorRepo.getById(AUTHOR.getId());
        assertEquals(author.getId(), AUTHOR.getId());
    }
}