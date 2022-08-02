package ru.otus.hw06library.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.hw06library.model.Book;
import ru.otus.hw06library.model.Comment;
import ru.otus.hw06library.service.BookService;
import ru.otus.hw06library.service.CommentService;
import ru.otus.hw06library.service.IOService;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class ShellCommands {

    private final BookService bookService;
    private final CommentService commentService;
    private final IOService ioService;

    @ShellMethod(value = "Создать новую книгу", key = {"c-b", "create-book"})
    public void createBook(String title, Long authorId, Long genreId) {
        Book savedBook = bookService.save(title, authorId, genreId);
        if (savedBook != null) ioService.print(String.format("Книга %s успешно сохранена.", savedBook));
    }

    @ShellMethod(value = "Удалить книгу по ID", key = {"d-b", "delete-book"})
    public void deleteBookById(Long id) {
        bookService.deleteById(id);
        ioService.print(String.format("Книга [%s] удалена.", id));
    }

    @ShellMethod(value = "Получить все книги", key = {"a-b", "all-books"})
    public void getAllBooks() {
        List<Book> books = bookService.getAll();
        for (Book book : books) {
            ioService.print(book.toString());
        }
    }

    @ShellMethod(value = "Получить книгу по ID", key = {"b", "book"})
    public void getBookById(Long id) {
        ioService.print(bookService.getById(id).toString());
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
    public void addComment(String entry, Long bookId) {
        Book book = bookService.getById(bookId);
        Comment comment = new Comment(entry, book);

        commentService.save(comment);
        ioService.print(String.format("Комментарий '%s' добавлен к книге '%s'", entry, bookId));
    }

    @ShellMethod(value = "Удалить комментарий к книге", key = {"d-comm", "delete-comment"})
    public void deleteCommentById(Long id) {
        commentService.deleteById(id);
        ioService.print(String.format("Комментарий '%s' удален", id));
    }

    @ShellMethod(value = "Получить все комментарии к книге", key = {"g-c", "get-comments"})
    public void getAllCommentsByBookId(Long bookId) {
        List<Comment> comments = commentService.getAllByBookId(bookId);
        for (Comment comment : comments) {
            ioService.print(comment.toString());
        }
    }
}