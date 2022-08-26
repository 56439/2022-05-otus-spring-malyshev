package ru.otus.hw09library.repo;

import org.springframework.data.repository.CrudRepository;
import ru.otus.hw09library.model.Comment;

public interface CommentRepo extends CrudRepository<Comment, Long> {
}