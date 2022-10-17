package ru.otus.ivan.repo.sql;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.ivan.model.sql.Book;

public interface BookH2Repo extends JpaRepository<Book, Long> {
}
