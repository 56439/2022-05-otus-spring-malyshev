package ru.otus.ivan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookEditDto {

    private long id;
    private String title;
    private Long author;
    private Long genre;

    public BookEditDto(String title, Long author, Long genre) {
        this.title = title;
        this.author = author;
        this.genre = genre;
    }
}
