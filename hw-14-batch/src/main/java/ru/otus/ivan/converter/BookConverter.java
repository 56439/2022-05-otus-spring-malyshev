package ru.otus.ivan.converter;

import ru.otus.ivan.model.mongo.BookMongo;
import ru.otus.ivan.model.sql.Book;

public interface BookConverter {
    BookMongo convert(Book book);
}
