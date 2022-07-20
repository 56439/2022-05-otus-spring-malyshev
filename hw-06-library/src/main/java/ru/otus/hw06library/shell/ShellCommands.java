package ru.otus.hw06library.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
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

    private Book newBook;

    @ShellMethod(value = "Создать новую книгу", key = {"c-b", "create-book"})
    @ShellMethodAvailability("creationAvailability")
    public void createBook(String title, String authorName, String genreName) {
        newBook = bookService.create(title, authorName, genreName);
        ioService.print(String.format("Книга %s готова к сохранению.", newBook));
    }

    @ShellMethod(value = "Сохранить созданную книгу", key = {"s-b", "save-book"})
    @ShellMethodAvailability("saveOrDenyAvailability")
    public void saveBook() {
        Book savedBook = bookService.save(newBook);
        ioService.print(String.format("Книга %s успешно сохранена.", savedBook));
    }

    @ShellMethod(value = "Отменить создание книги", key = {"d-c", "deny-creation"})
    @ShellMethodAvailability("saveOrDenyAvailability")
    public void denyCreation() {
        newBook = null;
    }

    @ShellMethod(value = "Удалить книгу по ID", key = {"d-b", "delete-book"})
    @ShellMethodAvailability("creationAvailability")
    public void deleteBookById(Long id) {
        if (bookService.deleteById(id)) {
            ioService.print(String.format("Книга [%s] удалена.", id));
        }
    }

    @ShellMethod(value = "Получить все книги", key = {"a-b", "all-books"})
    @ShellMethodAvailability("creationAvailability")
    public void getAllBooks() {
        List<Book> books = bookService.getAll();
        for (Book book : books) {
            ioService.print(book.toString());
        }
    }

    @ShellMethod(value = "Получить книгу по ID", key = {"b", "book"})
    @ShellMethodAvailability("creationAvailability")
    public void getBookById(Long id) {
        ioService.print(bookService.getById(id).toString());
    }

    @ShellMethod(value = "Получить книгу по названию", key = {"b-n", "book-by-name"})
    @ShellMethodAvailability("creationAvailability")
    public void getBookByName(String title) {
        ioService.print(bookService.getByTitle(title).toString());
    }

    @ShellMethod(value = "Получить книги по автору", key = {"b-a", "book-by-author"})
    @ShellMethodAvailability("creationAvailability")
    public void getBooksByAuthor(String author) {
        List<Book> books = bookService.getByAuthor(author);
        for (Book book : books) {
            ioService.print(book.toString());
        }
    }

    @ShellMethod(value = "Получить книги по жанру", key = {"b-g", "book-by-genre"})
    @ShellMethodAvailability("creationAvailability")
    public void getBooksByGenre(String genre) {
        List<Book> books = bookService.getByGenre(genre);
        for (Book book : books) {
            ioService.print(book.toString());
        }
    }

    @ShellMethod(value = "Добавить комментарий к книге", key = {"a-c", "add-comment"})
    @ShellMethodAvailability("creationAvailability")
    public void addComment(String entry, Long bookId) {
        Book book = bookService.getById(bookId);
        Comment comment = new Comment(entry, book);

        commentService.save(comment);
        ioService.print(String.format("Комментарий '%s' добавлен к книге '%s'", entry, book.getTitle()));
    }

    @ShellMethod(value = "Удалить комментарий к книге", key = {"d-comm", "delete-comment"})
    @ShellMethodAvailability("creationAvailability")
    public void deleteCommentById(Long id) {
        commentService.deleteById(id);
        ioService.print(String.format("Комментарий '%s' удален", id));
    }

    @ShellMethod(value = "Получить все комментарии к книге", key = {"g-c", "get-comments"})
    @ShellMethodAvailability("creationAvailability")
    public void getAllCommentsByBookId(Long bookId) {
        List<Comment> comments = commentService.getAllByBookId(bookId);
        for (Comment comment : comments) {
            ioService.print(comment.toString());
        }
    }

    private Availability creationAvailability() {
        if (newBook != null) {
            return Availability.unavailable(
                    "\nНевозможно выполнить действие во время создания книги!" +
                            "\nПодтвердите или отмените создание книги." +
                            "\nПодтвердить: 's-b', 'save-book'." +
                            "\nОтменить: 'd-c', 'deny-creation'.");
        }
        return Availability.available();
    }

    private Availability saveOrDenyAvailability() {
        if (newBook == null) {
            return Availability.unavailable(
                    "\nНевозможно выполнить действие!" +
                            "\nСоздайте книгу для использования команды.");
        }
        return Availability.available();
    }
}