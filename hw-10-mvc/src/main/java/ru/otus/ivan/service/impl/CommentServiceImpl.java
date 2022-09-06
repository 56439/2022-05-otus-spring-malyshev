package ru.otus.ivan.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.ivan.dto.CommentDto;
import ru.otus.ivan.dto.CommentEditDto;
import ru.otus.ivan.model.Book;
import ru.otus.ivan.model.Comment;
import ru.otus.ivan.repo.BookRepo;
import ru.otus.ivan.repo.CommentRepo;
import ru.otus.ivan.service.CommentService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentServiceImpl implements CommentService {

    private final CommentRepo commentRepo;
    private final BookRepo bookRepo;

    @Override
    public CommentDto save(CommentEditDto commentDto) {
        Book book = bookRepo.findById(commentDto.getBook()).orElseThrow();
        Comment comment = new Comment(commentDto.getEntry(), book);

        return CommentDto.fromModel(commentRepo.save(comment));
    }

    @Override
    public boolean deleteById(Long id) {
        try {
            commentRepo.deleteById(id);
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public List<CommentDto> getAllByBookId(Long bookId) {
        Book book = bookRepo.findById(bookId).orElseThrow();
        return CommentDto.fromModelsList(book.getComments());
    }
}