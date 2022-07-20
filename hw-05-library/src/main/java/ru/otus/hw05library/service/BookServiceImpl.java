package ru.otus.hw05library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.hw05library.dao.AuthorDao;
import ru.otus.hw05library.dao.BookDao;
import ru.otus.hw05library.dao.GenreDao;
import ru.otus.hw05library.model.Book;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;

    @Override
    public Book save(Book book) {
        if (book.getAuthor().getId() == null) {
            book.setAuthor(authorDao.insert(book.getAuthor()));
        }
        if (book.getGenre().getId() == null) {
            book.setGenre(genreDao.insert(book.getGenre()));
        }
        return bookDao.insert(book);
    }

    @Override
    public boolean deleteById(int id) {
        return bookDao.deleteById(id);
    }

    @Override
    public List<Book> getAll() {
        return bookDao.getAll();
    }

    @Override
    public Book getById(int id) {
        return bookDao.getById(id);
    }

    @Override
    public Book getByName(String name) {
        return bookDao.getByName(name);
    }

    @Override
    public List<Book> getByAuthor(String author) {
        return bookDao.getByAuthor(author);
    }

    @Override
    public List<Book> getByGenre(String genre) {
        return bookDao.getByGenre(genre);
    }
}