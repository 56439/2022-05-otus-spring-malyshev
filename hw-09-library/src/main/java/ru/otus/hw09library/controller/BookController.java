package ru.otus.hw09library.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.hw09library.dto.*;
import ru.otus.hw09library.service.AuthorService;
import ru.otus.hw09library.service.BookService;
import ru.otus.hw09library.service.CommentService;
import ru.otus.hw09library.service.GenreService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;
    private final CommentService commentService;

    @GetMapping("/")
    public String listBooks(Model model) {
        List<BookDto> books = bookService.getAll();
        model.addAttribute("books", books);
        return "books";
    }

    @GetMapping("/editBook")
    public String editBook(@RequestParam("id") long id, Model model) {
        BookDto book = bookService.getById(id);
        List<AuthorDto> authors = authorService.getAll();
        List<GenreDto> genres = genreService.getAll();

        model.addAttribute("book",book);
        model.addAttribute("authors", authors);
        model.addAttribute("genres", genres);

        return "editBook";
    }

    @Validated
    @PostMapping("/editBook")
    public String saveBook(@Valid @ModelAttribute("book") BookEditDto book,
                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("ERROR");
            return "editBook";
        }
        bookService.update(book);
        return "redirect:/";
    }

    @PostMapping("/delete")
    public String deleteBook(@RequestParam(name = "id") long id) {
        bookService.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/addBook")
    public String addBook(Model model){
        List<AuthorDto> authors = authorService.getAll();
        List<GenreDto> genres = genreService.getAll();

        model.addAttribute("authors", authors);
        model.addAttribute("genres", genres);

        return "addBook";
    }

    @Validated
    @PostMapping("/addBook")
    public String createBook(@Valid @ModelAttribute("book") BookEditDto book,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("ERROR");
            return "addBook";
        }
        bookService.save(book);
        return "redirect:/";
    }

    @GetMapping("/comments")
    public String commentsList(@RequestParam("id") long id, Model model) {
        List<CommentDto> comments = commentService.getAllByBookId(id);

        model.addAttribute("comments", comments);
        model.addAttribute("bookId", id);

        return "comments";
    }

    @Validated
    @PostMapping("/comments/delete")
    public String deleteComment(@Valid @RequestParam("id") long id) {
        commentService.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/comments/addComment")
    public String addComment(@RequestParam("bookId") long id, Model model) {
        model.addAttribute("bookId", id);
        return "addComment";
    }

    @Validated
    @PostMapping("/comments/addComment")
    public String createComment(@RequestParam("bookId") long id,
                                @Valid @ModelAttribute("comment") CommentEditDto comment,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("ERROR");
            return "addComment";
        }
        comment.setBook(id);
        commentService.save(comment);
        log.info(comment.toString());
        return "redirect:/comments?id=" + id;
    }
}