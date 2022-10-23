package ru.otus.ivan.service;

import ru.otus.ivan.dto.AuthorDto;

import java.util.List;

public interface AuthorService {

    AuthorDto save(AuthorDto author);

    List<AuthorDto> getAll();

    AuthorDto getById(long id);
}