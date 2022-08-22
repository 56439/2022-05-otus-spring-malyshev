package ru.otus.hw09library.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.hw09library.dto.GenreDto;
import ru.otus.hw09library.model.Genre;
import ru.otus.hw09library.repo.GenreRepo;
import ru.otus.hw09library.service.GenreService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepo genreRepo;

    @Override
    public GenreDto save(GenreDto genreDto) {
        Genre genre = genreRepo.save(genreDto.toModel());
        return GenreDto.fromModel(genre);
    }

    @Override
    public List<GenreDto> getAll() {
        List<Genre> genres = genreRepo.findAll();
        return GenreDto.fromModelsList(genres);
    }

    @Override
    public GenreDto getById(long id) {
        Genre genre = genreRepo.findById(id).orElseThrow();
        return GenreDto.fromModel(genre);
    }
}
