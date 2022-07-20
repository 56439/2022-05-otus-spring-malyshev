package ru.otus.hw05library.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.hw05library.dao.AuthorDaoJdbc;
import ru.otus.hw05library.dao.BookDaoJdbc;
import ru.otus.hw05library.dao.GenreDaoJdbc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.otus.hw05library.TestData.NEW_BOOK_2;

@SpringBootTest
@DisplayName(value = "Сервис для работы с книгами")
public class BookServiceImplTest {

    @Autowired
    BookServiceImpl bookService;

    @Autowired
    AuthorDaoJdbc authorDao;

    @Autowired
    GenreDaoJdbc genreDao;

    @Autowired
    BookDaoJdbc bookDao;

    @Test
    @DisplayName(value = "сохраняет книгу с автором и жанром в базу")
    void save() {
        int expectedAuthorsCount = authorDao.count() + 1;
        int expectedGenresCount = genreDao.count() + 1;
        int expectedBooksCount = bookDao.count() + 1;

        bookService.save(NEW_BOOK_2);

        assertEquals(expectedAuthorsCount, authorDao.count());
        assertEquals(expectedGenresCount, genreDao.count());
        assertEquals(expectedBooksCount, bookDao.count());
    }
}