package ru.otus.hw08library.repo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.hw08library.model.Author;
import ru.otus.hw08library.model.Book;
import ru.otus.hw08library.model.Genre;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.otus.hw08library.TestData.AUTHOR_BOOKS_COUNT;
import static ru.otus.hw08library.TestData.GENRE_BOOKS_COUNT;

@SpringBootTest
@DisplayName("Репозиторий для работы с книгами")
public class BookRepoTest {

    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private GenreRepo genreRepo;

    @Autowired
    private AuthorRepo authorRepo;

    @Test
    @DisplayName("достает из базы книгу по названию")
    void findByTitle() {
        String title = "book 1";
        Book book = bookRepo.findByTitle(title);

        assertEquals(title, book.getTitle());
    }

    @Test
    @DisplayName("достает из базы список книг по автору")
    void findByAuthor() {
        String authorName = "author";
        Author author = authorRepo.findByName(authorName);
        List<Book> books = bookRepo.findByAuthor(author);

        assertEquals(AUTHOR_BOOKS_COUNT, books.size());
    }

    @Test
    @DisplayName("достает из базы список книг по жанру")
    void findByGenresContains() {
        String genreName = "genre";
        Genre genre = genreRepo.findByName(genreName);
        List<Book> books = bookRepo.findByGenresContains(genre);

        assertEquals(GENRE_BOOKS_COUNT, books.size());
    }
}
