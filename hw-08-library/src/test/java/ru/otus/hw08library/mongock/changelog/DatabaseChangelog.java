package ru.otus.hw08library.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import ru.otus.hw08library.model.Author;
import ru.otus.hw08library.model.Book;
import ru.otus.hw08library.model.Comment;
import ru.otus.hw08library.model.Genre;
import ru.otus.hw08library.repo.AuthorRepo;
import ru.otus.hw08library.repo.BookRepo;
import ru.otus.hw08library.repo.GenreRepo;

import java.util.List;

import static java.util.List.of;

@ChangeLog
public class DatabaseChangelog {

    @ChangeSet(order = "001", id = "dropDb", author = "ivan", runAlways = true)
    public void dropDb(MongoDatabase db) {
        db.drop();
    }

    @ChangeSet(order = "002", id = "insertAuthors", author = "ivan")
    public void insertAuthors(AuthorRepo authorRepo) {
        var authors = of(
                new Author("author"),
                new Author("Александр Пушкин"),
                new Author("Владимир Маяковский")
        );

        authorRepo.saveAll(authors);
    }

    @ChangeSet(order = "003", id = "insertGenres", author = "ivan")
    public void insertGenres(GenreRepo genreRepo) {
        var genres = of(
                new Genre("genre"),
                new Genre("Роман"),
                new Genre("Повесть")
        );

        genreRepo.saveAll(genres);
    }

    @ChangeSet(order = "004", id = "insertBooks", author = "ivan")
    public void insertBooks(AuthorRepo authorRepo, BookRepo bookRepo, GenreRepo genreRepo) {
        Author author1 = authorRepo.findByName("author");
        Author author2 = authorRepo.findByName("Александр Пушкин");

        List<Genre> genres1 = of(genreRepo.findByName("genre"), genreRepo.findByName("Повесть"));
        List<Genre> genres2 = of(genreRepo.findByName("Роман"));

        List<Comment> comments1 = of(new Comment("comment 1"), new Comment("comment 2"));
        List<Comment> comments2 = of(new Comment("comment 3"));

        var books = of(
                new Book("book 1", author1, genres1),
                new Book("book 2", author2, genres1, comments1),
                new Book("book 3", author1, genres2, comments2)
        );

        bookRepo.saveAll(books);
    }
}
