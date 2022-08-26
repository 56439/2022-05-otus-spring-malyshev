package ru.otus.hw09library.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import ru.otus.hw09library.model.Book;

import java.util.List;

public interface BookRepo extends CrudRepository<Book, Long> {

    Book getByTitle(String title);
    List<Book> getByAuthor_Name(String authorName);
    List<Book> getByGenre_Name(String genreName);
    List<Book> findAll();
}