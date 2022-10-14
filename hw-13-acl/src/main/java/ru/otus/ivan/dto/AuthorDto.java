package ru.otus.ivan.dto;

import lombok.*;
import ru.otus.ivan.model.Author;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AuthorDto {

    private Long id;
    private String name;

    public Author toModel() {
        return new Author(id, name);
    }

    public static AuthorDto fromModel(Author author) {
        return new AuthorDto(author.getId(), author.getName());
    }

    public static List<AuthorDto> fromModelsList(List<Author> authors) {
        List<AuthorDto> authorDtos = new ArrayList<>();
        for (Author author : authors) {
            authorDtos.add(fromModel(author));
        }
        return authorDtos;
    }
}
