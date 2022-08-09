package ru.otus.hw06library.service;

import ru.otus.hw06library.model.Comment;

import java.util.List;

public interface CommentService {

    Comment save(Comment comment);

    void deleteById(Long id);

    List<Comment> getAllByBookId(Long bookId);
}