package ru.otus.hw09library.service;

import ru.otus.hw09library.dto.AuthorDto;

import java.util.List;

public interface AuthorService {

    AuthorDto save(AuthorDto author);

    List<AuthorDto> getAll();

    AuthorDto getById(long id);
}