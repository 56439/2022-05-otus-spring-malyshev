package ru.otus.hw08library.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@ToString
@RequiredArgsConstructor
@Document(collection = "authors")
public class Author {

    @Id
    private String id;

    private String name;

    public Author(String name) {
        this.name = name;
    }
}
