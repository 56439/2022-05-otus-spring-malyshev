package ru.otus.ivan.service;

import ru.otus.ivan.dto.GenreDto;

import java.util.List;

public interface GenreService {

    GenreDto save(GenreDto genre);

    List<GenreDto> getAll();

    GenreDto getById(long id);
}
