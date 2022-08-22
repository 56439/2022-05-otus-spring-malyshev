package ru.otus.hw09library.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.hw09library.dto.CommentDto;
import ru.otus.hw09library.dto.CommentEditDto;
import ru.otus.hw09library.model.Book;
import ru.otus.hw09library.model.Comment;
import ru.otus.hw09library.repo.BookRepo;
import ru.otus.hw09library.repo.CommentRepo;
import ru.otus.hw09library.service.CommentService;

import java.util.List;

@Service
@RequiredArgsConstructor
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
    public void deleteById(Long id) {
        commentRepo.deleteById(id);
    }

    @Override
    public List<CommentDto> getAllByBookId(Long bookId) {
        Book book = bookRepo.findById(bookId).orElseThrow();
        return CommentDto.fromModelsList(book.getComments());
    }
}