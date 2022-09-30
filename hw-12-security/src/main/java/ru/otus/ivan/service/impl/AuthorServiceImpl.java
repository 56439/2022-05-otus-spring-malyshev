package ru.otus.ivan.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.ivan.dto.AuthorDto;
import ru.otus.ivan.model.Author;
import ru.otus.ivan.repo.AuthorRepo;
import ru.otus.ivan.service.AuthorService;

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
