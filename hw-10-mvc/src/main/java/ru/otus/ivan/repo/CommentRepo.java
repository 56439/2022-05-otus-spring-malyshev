package ru.otus.ivan.repo;

import org.springframework.data.repository.CrudRepository;
import ru.otus.ivan.model.Comment;

public interface CommentRepo extends CrudRepository<Comment, Long> {
}