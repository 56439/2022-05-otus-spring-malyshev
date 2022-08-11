package ru.otus.hw07library.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.hw07library.model.Book;
import ru.otus.hw07library.model.Comment;
import ru.otus.hw07library.repo.BookRepo;
import ru.otus.hw07library.repo.CommentRepo;
import ru.otus.hw07library.service.CommentService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepo commentRepo;
    private final BookRepo bookRepo;

    @Override
    public Comment save(Comment comment) {
        return commentRepo.save(comment);
    }

    @Override
    public void deleteById(Long id) {
        commentRepo.deleteById(id);
    }

    @Override
    public List<Comment> getAllByBookId(Long bookId) {
        Book book = bookRepo.findById(bookId).orElseThrow();
        return book.getComments();
    }
}