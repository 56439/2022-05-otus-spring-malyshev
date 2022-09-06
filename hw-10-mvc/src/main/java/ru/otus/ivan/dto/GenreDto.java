package ru.otus.ivan.dto;

import lombok.*;
import ru.otus.ivan.model.Genre;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GenreDto {

    private Long id;
    private String name;

    public Genre toModel() {
        return new Genre(id, name);
    }

    public static GenreDto fromModel(Genre genre) {
        return new GenreDto(genre.getId(), genre.getName());
    }

    public static List<GenreDto> fromModelsList(List<Genre> genres) {
        List<GenreDto> genreDtos = new ArrayList<>();
        for (Genre genre :genres) {
            genreDtos.add(fromModel(genre));
        }
        return genreDtos;
    }
}
