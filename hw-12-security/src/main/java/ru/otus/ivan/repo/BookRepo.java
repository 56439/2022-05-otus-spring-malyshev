package ru.otus.ivan.repo;

import org.springframework.data.repository.CrudRepository;
import ru.otus.ivan.model.Book;

import java.util.List;

public interface BookRepo extends CrudRepository<Book, Long> {

    Book getByTitle(String title);
    List<Book> getByAuthor_Name(String authorName);
    List<Book> getByGenre_Name(String genreName);
    List<Book> findAll();
}