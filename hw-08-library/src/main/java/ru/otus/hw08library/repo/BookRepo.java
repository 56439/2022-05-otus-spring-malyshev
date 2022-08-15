package ru.otus.hw08library.repo;

import org.springframework.data.repository.CrudRepository;
import ru.otus.hw08library.model.Author;
import ru.otus.hw08library.model.Book;
import ru.otus.hw08library.model.Genre;

import java.util.List;

public interface BookRepo extends CrudRepository<Book, Integer> {

    Book findByTitle(String title);
    List<Book> findByAuthor(Author author);
    List<Book> findByGenresContains(Genre genre);
    List<Book> findAll();
}
