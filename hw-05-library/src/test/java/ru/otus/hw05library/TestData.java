package ru.otus.hw05library;

import ru.otus.hw05library.model.Author;
import ru.otus.hw05library.model.Book;
import ru.otus.hw05library.model.Genre;

public class TestData {

    public static final Integer AUTHOR_BOOKS_COUNT = 1;
    public static final Integer GENRE_BOOKS_COUNT = 1;

    public static final Author AUTHOR = new Author(1L, "author");
    public static final Genre GENRE = new Genre(1L, "genre");
    public static final Book BOOK = new Book(1L, "book", AUTHOR, GENRE);

    public static final Author NEW_AUTHOR = new Author("new author");
    public static final Genre NEW_GENRE = new Genre("new genre");
    public static final Book NEW_BOOK_1 = new Book("new book 1", AUTHOR, GENRE);
    public static final Book NEW_BOOK_2 = new Book("new book 2", NEW_AUTHOR, NEW_GENRE);
}