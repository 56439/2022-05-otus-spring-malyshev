package ru.otus.ivan.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import ru.otus.ivan.model.Book;

import java.util.List;

public interface BookRepo extends JpaRepository<Book, Long> {

    @PostFilter("hasPermission(filterObject, 'READ')")
    List<Book> findAll();

    @PreAuthorize("hasPermission(#book, 'WRITE')")
    Book save(Book book);

    @PostAuthorize("hasPermission(returnObject, 'READ')")
    Book getById(Long id);
}