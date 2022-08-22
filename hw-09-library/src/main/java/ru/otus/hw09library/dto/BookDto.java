package ru.otus.hw09library.dto;

import lombok.*;
import ru.otus.hw09library.model.Book;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookDto {

    private long id;
    private String title;
    private AuthorDto author;
    private GenreDto genre;
    private List<CommentDto> comments;

    public BookDto(long id, String title, AuthorDto author, GenreDto genre) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
    }

    public Book toModel() {
        return new Book(id, title, author.toModel(), genre.toModel());
    }

    public static BookDto fromModel(Book book) {
        AuthorDto authorDto = AuthorDto.fromModel(book.getAuthor());
        GenreDto genreDto = GenreDto.fromModel(book.getGenre());

        return new BookDto(book.getId(), book.getTitle(), authorDto, genreDto);
    }

    public static BookDto fromModelWithComments(Book book) {
        AuthorDto authorDto = AuthorDto.fromModel(book.getAuthor());
        GenreDto genreDto = GenreDto.fromModel(book.getGenre());
        List<CommentDto> commentDtos = CommentDto.fromModelsList(book.getComments());

        return new BookDto(book.getId(), book.getTitle(), authorDto, genreDto, commentDtos);
    }

    public static List<BookDto> fromModelsList(List<Book> books) {
        List<BookDto> bookDtos = new ArrayList<>();
        for (Book book : books) {
            bookDtos.add(fromModel(book));
        }
        return bookDtos;
    }
}