package ru.otus.hw07library.service;

import ru.otus.hw07library.model.Comment;

import java.util.List;

public interface CommentService {

    Comment save(Comment comment);

    void deleteById(Long id);

    List<Comment> getAllByBookId(Long id);
}