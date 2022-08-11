package ru.otus.hw07library.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.hw07library.model.Comment;

import java.util.List;

public interface CommentRepo extends JpaRepository<Comment, Long> {
}