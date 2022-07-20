package ru.otus.hw06library.repo;

import ru.otus.hw06library.model.Comment;

import java.util.List;

public interface CommentRepo {

    Comment saveOrUpdate(Comment comment);

    boolean deleteById(Long id);

    List<Comment> getAllByBookId(Long bookId);

    Comment getById(Long id);

    Long count();
}