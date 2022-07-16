package ru.otus.hw05library.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class Genre {

    private Long id;
    private String name;

    public Genre(String name) {
        this.name = name;
    }
}