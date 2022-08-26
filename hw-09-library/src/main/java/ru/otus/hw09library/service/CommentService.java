package ru.otus.hw09library.service;

import ru.otus.hw09library.dto.CommentDto;
import ru.otus.hw09library.dto.CommentEditDto;

import java.util.List;

public interface CommentService {

    CommentDto save(CommentEditDto comment);

    void deleteById(Long id);

    List<CommentDto> getAllByBookId(Long id);
}