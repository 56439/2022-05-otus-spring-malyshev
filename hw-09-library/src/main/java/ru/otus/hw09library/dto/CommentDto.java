package ru.otus.hw09library.dto;

import lombok.*;
import ru.otus.hw09library.model.Book;
import ru.otus.hw09library.model.Comment;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CommentDto {

    private long id;
    private String entry;
    private BookDto bookDto;

    public Comment toModel() {
        return new Comment(id, entry, bookDto.toModel());
    }

    public static CommentDto fromModel(Comment comment) {
        Book book = comment.getBook();
        return new CommentDto(comment.getId(), comment.getEntry(), BookDto.fromModel(book));
    }

    public static List<CommentDto> fromModelsList(List<Comment> comments) {
        List<CommentDto> commentDtos = new ArrayList<>();
        for (Comment comment : comments) {
            commentDtos.add(fromModel(comment));
        }
        return commentDtos;
    }
}
