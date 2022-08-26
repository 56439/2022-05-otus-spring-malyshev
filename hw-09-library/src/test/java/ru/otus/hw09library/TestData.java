package ru.otus.hw09library;

import ru.otus.hw09library.dto.AuthorDto;
import ru.otus.hw09library.dto.BookDto;
import ru.otus.hw09library.dto.BookEditDto;
import ru.otus.hw09library.dto.GenreDto;

public class TestData {

    public final static AuthorDto AUTHOR_DTO = new AuthorDto( 1L, "author");
    public final static GenreDto GENRE_DTO = new GenreDto(1L, "genre");
    public final static BookDto BOOK_DTO = new BookDto(1L, "book", AUTHOR_DTO, GENRE_DTO);

    public final static BookEditDto ADD_BOOK_EDIT_DTO = new BookEditDto("new_book", 1L, 1L);
    public final static BookEditDto UPDATE_BOOK_EDIT_DTO = new BookEditDto(2L, "updated_book", 1L, 1L);

}
