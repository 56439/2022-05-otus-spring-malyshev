package ru.otus.hw05library.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import ru.otus.hw05library.dao.AuthorDao;
import ru.otus.hw05library.dao.GenreDao;
import ru.otus.hw05library.model.Author;
import ru.otus.hw05library.model.Book;
import ru.otus.hw05library.model.Genre;
import ru.otus.hw05library.service.BookService;
import ru.otus.hw05library.service.IOService;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class ShellCommands {

    private final BookService bookService;
    private final IOService ioService;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;

    private Book newBook;

    @ShellMethod(value = "Создать новую книгу", key = {"c-b", "create-book"})
    @ShellMethodAvailability("creationAvailability")
    public void createBook(String name, String authorName, String genreName) {
        Book book = bookService.getByName(name);
        if (book != null) {
            ioService.print(String.format("Книга %s уже существует!", name));
            return;
        }

        Author author = authorDao.getByName(authorName);
        if (author == null) {
            ioService.print(String.format("Автор '%s' не существует. Будет создан новый.", authorName));
            author = new Author(authorName);
        }

        Genre genre = genreDao.getByName(genreName);
        if (genre == null) {
            ioService.print(String.format("Жанр '%s' не существует. Будет создан новый.", genreName));
            genre = new Genre(genreName);
        }

        newBook = new Book(name, author, genre);
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
    public void deleteBookById(int id) {
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
    public void getBookById(int id) {
        ioService.print(bookService.getById(id).toString());
    }

    @ShellMethod(value = "Получить книгу по названию", key = {"b-n", "book-by-name"})
    @ShellMethodAvailability("creationAvailability")
    public void getBookByName(String name) {
        ioService.print(bookService.getByName(name).toString());
    }

    @ShellMethod(value = "Получить книги по автору", key = {"b-a", "book-by-author"})
    @ShellMethodAvailability("creationAvailability")
    public void getBooksByAuthor(String author) {
        List<Book> books = bookService.getByAuthor(author);
        ioService.print("количество книг " + books.size());
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