package ru.otus.ivan.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.ivan.model.Comment;

public interface CommentRepo extends JpaRepository<Comment, Long> {
}