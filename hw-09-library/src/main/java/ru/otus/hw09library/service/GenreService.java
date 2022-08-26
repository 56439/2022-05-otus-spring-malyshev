package ru.otus.hw09library.service;

import ru.otus.hw09library.dto.GenreDto;

import java.util.List;

public interface GenreService {

    GenreDto save(GenreDto genre);

    List<GenreDto> getAll();

    GenreDto getById(long id);
}
