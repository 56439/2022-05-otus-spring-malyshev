package ru.otus.hw07library.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.hw07library.model.Book;

import java.util.List;

public interface BookRepo extends JpaRepository<Book, Long> {

    Book getByTitle(String title);
    List<Book> getByAuthor_Name(String authorName);
    List<Book> getByGenre_Name(String genreName);
}