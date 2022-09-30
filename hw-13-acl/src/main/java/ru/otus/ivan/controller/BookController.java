package ru.otus.ivan.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.otus.ivan.dto.BookDto;
import ru.otus.ivan.dto.BookEditDto;
import ru.otus.ivan.service.BookService;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/books")
    public List<BookDto> getAllBooks() {
        return bookService.getAll();
    }

    @GetMapping("/book/{id}")
    public BookDto getBookById(@PathVariable Long id) {
        return bookService.getById(id);
    }

    @PostMapping("/book/delete/{id}")
    public void deleteBookById(@PathVariable Long id) {
        bookService.deleteById(id);
    }

    @PostMapping(value = "/book/add", headers = "Accept=application/json")
    public void addBook(@RequestBody BookEditDto book) {
        bookService.save(book);
    }

    @PostMapping(value = "/book/update", headers = "Accept=application/json")
    public void updateBook(@RequestBody BookEditDto book) {
        bookService.update(book);
    }
}