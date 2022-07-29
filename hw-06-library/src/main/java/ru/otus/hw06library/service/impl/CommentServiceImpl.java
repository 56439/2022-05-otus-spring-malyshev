package ru.otus.hw06library.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw06library.model.Book;
import ru.otus.hw06library.model.Comment;
import ru.otus.hw06library.repo.BookRepo;
import ru.otus.hw06library.repo.CommentRepo;
import ru.otus.hw06library.service.CommentService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepo commentRepo;
    private final BookRepo bookRepo;

    @Override
    @Transactional
    public Comment save(Comment comment) {
        return commentRepo.saveOrUpdate(comment);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        commentRepo.deleteById(id);
    }

    @Override
    public List<Comment> getAllByBookId(Long bookId) {
        Book book = bookRepo.getById(bookId);
        return book.getComments();
    }
}