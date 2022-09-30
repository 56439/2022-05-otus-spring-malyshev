package ru.otus.ivan;

import ru.otus.ivan.dto.*;
import ru.otus.ivan.model.Author;
import ru.otus.ivan.model.Book;
import ru.otus.ivan.model.Genre;

import java.util.List;

public class TestData {

    // MODELS
    public final static Author AUTHOR = new Author(1L, "author");
    public final static Genre GENRE = new Genre(1L, "genre");
    public final static Book BOOK = new Book(1L, "book", AUTHOR, GENRE);

    // DTOs
    public final static AuthorDto AUTHOR_DTO = new AuthorDto(1L, "author");
    public final static GenreDto GENRE_DTO = new GenreDto(1L, "genre");
    public final static BookDto BOOK_DTO = new BookDto(1L, "book", AUTHOR_DTO, GENRE_DTO);
    public final static BookDto BOOK_DTO_2 = new BookDto(2L, "book_2", AUTHOR_DTO, GENRE_DTO);
    public final static BookDto BOOK_DTO_3 = new BookDto(3L, "book_3", AUTHOR_DTO, GENRE_DTO);
    public final static List<BookDto> BOOKS_DTO_LIST = List.of(BOOK_DTO, BOOK_DTO_2, BOOK_DTO_3);

    //EDIT DTOs
    public final static BookEditDto BOOK_EDIT_DTO = new BookEditDto(1L, "book", 1L, 1L);
    public final static CommentEditDto COMMENT_EDIT_DTO = new CommentEditDto("comment", 1L);
}