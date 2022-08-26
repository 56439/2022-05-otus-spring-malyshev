package ru.otus.hw09library.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.hw09library.dto.AuthorDto;
import ru.otus.hw09library.model.Author;
import ru.otus.hw09library.repo.AuthorRepo;
import ru.otus.hw09library.service.AuthorService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepo authorRepo;

    @Override
    public AuthorDto save(AuthorDto authorDto) {
        Author author = authorRepo.save(authorDto.toModel());
        return AuthorDto.fromModel(author);
    }

    @Override
    public List<AuthorDto> getAll() {
        List<Author> authors = authorRepo.findAll();
        return AuthorDto.fromModelsList(authors);
    }

    @Override
    public AuthorDto getById(long id) {
        Author author = authorRepo.findById(id).orElseThrow();
        return AuthorDto.fromModel(author);
    }
}
