package ru.otus.hw08library.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.hw08library.model.Book;
import ru.otus.hw08library.model.Comment;
import ru.otus.hw08library.service.BookService;
import ru.otus.hw08library.service.IOService;

import java.util.Collections;
import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class ShellCommands {

    private final BookService bookService;
    private final IOService ioService;

    @ShellMethod(value = "Создать новую книгу", key = {"c-b", "create-book"})
    public void createBook(String title, String authorName, List<String> genres) {
        Book savedBook = bookService.save(title, authorName, genres);
        if (savedBook != null) ioService.print(String.format("Книга %s успешно сохранена.", savedBook));
    }

    @ShellMethod(value = "Удалить книгу", key = {"d-b", "delete-book"})
    public void deleteBookById(Book book) {
        bookService.delete(book);
        ioService.print(String.format("Книга [%s] удалена.", book));
    }

    @ShellMethod(value = "Получить все книги", key = {"a-b", "all-books"})
    public void getAllBooks() {
        List<Book> books = bookService.getAll();
        for (Book book : books) {
            ioService.print(book.toString());
        }
    }

    @ShellMethod(value = "Получить книгу по названию", key = {"b-n", "book-by-name"})
    public void getBookByName(String title) {
        ioService.print(bookService.getByTitle(title).toString());
    }

    @ShellMethod(value = "Получить книги по автору", key = {"b-a", "book-by-author"})
    public void getBooksByAuthor(String author) {
        List<Book> books = bookService.getByAuthor(author);
        for (Book book : books) {
            ioService.print(book.toString());
        }
    }

    @ShellMethod(value = "Получить книги по жанру", key = {"b-g", "book-by-genre"})
    public void getBooksByGenre(String genre) {
        List<Book> books = bookService.getByGenre(genre);
        for (Book book : books) {
            ioService.print(book.toString());
        }
    }

    @ShellMethod(value = "Добавить комментарий к книге", key = {"a-c", "add-comment"})
    public void addComment(String entry, String bookTitle) {
        Book book = bookService.getByTitle(bookTitle);
        Comment comment = new Comment(entry);
        book.addComment(comment);

        bookService.update(book);
        ioService.print(String.format("Комментарий '%s' добавлен к книге '%s'", entry, book.getTitle()));
    }

    @ShellMethod(value = "Удалить все комментарии к книге", key = {"d-comm", "delete-comments"})
    public void deleteComments(String bookTitle) {
        Book book = bookService.getByTitle(bookTitle);
        book.setComments(Collections.emptyList());

        bookService.update(book);
        ioService.print("Комментарии удалены");
    }

    @ShellMethod(value = "Получить все комментарии к книге", key = {"g-c", "get-comments"})
    public void getAllComments(String bookTitle) {
        Book book = bookService.getByTitle(bookTitle);
        List<Comment> comments = book.getComments();
        for (Comment comment : comments) {
            ioService.print(comment.toString());
        }
    }
}
