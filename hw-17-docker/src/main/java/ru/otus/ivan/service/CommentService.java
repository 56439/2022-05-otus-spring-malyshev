package ru.otus.ivan.service;

import ru.otus.ivan.dto.CommentDto;
import ru.otus.ivan.dto.CommentEditDto;

import java.util.List;

public interface CommentService {

    CommentDto save(CommentEditDto comment);

    boolean deleteById(Long id);

    List<CommentDto> getAllByBookId(Long id);
}