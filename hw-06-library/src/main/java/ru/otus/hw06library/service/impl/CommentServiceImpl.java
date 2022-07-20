package ru.otus.hw06library.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw06library.model.Comment;
import ru.otus.hw06library.repo.CommentRepo;
import ru.otus.hw06library.service.CommentService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepo commentRepo;

    @Override
    @Transactional
    public Comment save(Comment comment) {
        return commentRepo.saveOrUpdate(comment);
    }

    @Override
    @Transactional
    public boolean deleteById(Long id) {
        return commentRepo.deleteById(id);
    }

    @Override
    @Transactional
    public List<Comment> getAllByBookId(Long id) {
        return commentRepo.getAllByBookId(id);
    }
}