package ru.otus.ivan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CommentEditDto {

    private long id;
    private String entry;
    private long book;

    public CommentEditDto(String entry, long bookId) {
        this.entry = entry;
        this.book = bookId;
    }
}
