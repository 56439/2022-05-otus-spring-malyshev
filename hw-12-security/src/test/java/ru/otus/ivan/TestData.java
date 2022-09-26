package ru.otus.ivan;

import ru.otus.ivan.dto.*;
import ru.otus.ivan.model.Author;
import ru.otus.ivan.model.Book;
import ru.otus.ivan.model.Comment;
import ru.otus.ivan.model.Genre;

import java.util.List;

public class TestData {

    // MODELS
    public final static Author AUTHOR = new Author(1L, "author");
    public final static Genre GENRE = new Genre(1L, "genre");
    public final static Book BOOK = new Book(1L, "book", AUTHOR, GENRE);
    public final static Book BOOK_2 = new Book(2L, "book_2", AUTHOR, GENRE);
    public final static Book BOOK_3 = new Book(3L, "book_3", AUTHOR, GENRE);
    public final static List<Book> BOOKS_LIST = List.of(BOOK, BOOK_2, BOOK_3);
//    public final static Comment COMMENT = new Comment(1L, "comment", BOOK);
//    public final static Comment COMMENT_2 = new Comment( 2L,"comment_2", BOOK);
//    public final static Comment COMMENT_3 = new Comment( 3L, "comment_3", BOOK);
//    public final static List<Comment> COMMENTS = List.of(COMMENT, COMMENT_2, COMMENT_3);
//    public final static Book BOOK_WITH_COMMENTS = new Book(1L, "book", AUTHOR, GENRE, COMMENTS);

    // DTOs
    public final static AuthorDto AUTHOR_DTO = new AuthorDto(1L, "author");
    public final static GenreDto GENRE_DTO = new GenreDto(1L, "genre");
    public final static BookDto BOOK_DTO = new BookDto(1L, "book", AUTHOR_DTO, GENRE_DTO);
    public final static BookDto BOOK_DTO_2 = new BookDto(2L, "book_2", AUTHOR_DTO, GENRE_DTO);
    public final static BookDto BOOK_DTO_3 = new BookDto(3L, "book_3", AUTHOR_DTO, GENRE_DTO);
    public final static List<BookDto> BOOKS_DTO_LIST = List.of(BOOK_DTO, BOOK_DTO_2, BOOK_DTO_3);
//    public final static CommentDto COMMENT_DTO = new CommentDto(1L, "comment", BOOK_DTO);
//    public final static CommentDto COMMENT_DTO_2 = new CommentDto(2L, "comment_2", BOOK_DTO);
//    public final static CommentDto COMMENT_DTO_3 = new CommentDto(3L, "comment_3", BOOK_DTO);
//    public final static List<CommentDto> COMMENTS_DTO_LIST = List.of(COMMENT_DTO, COMMENT_DTO_2, COMMENT_DTO_3);

    //EDIT DTOs
    public final static BookEditDto BOOK_EDIT_DTO = new BookEditDto(1L, "book", 1L, 1L);
    public final static CommentEditDto COMMENT_EDIT_DTO = new CommentEditDto("comment", 1L);
}