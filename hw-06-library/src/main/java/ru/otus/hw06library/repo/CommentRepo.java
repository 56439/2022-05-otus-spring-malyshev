package ru.otus.hw06library.repo;

import ru.otus.hw06library.model.Comment;

public interface CommentRepo {

    Comment saveOrUpdate(Comment comment);

    void deleteById(Long id);

    Comment getById(Long id);

    Long count();
}