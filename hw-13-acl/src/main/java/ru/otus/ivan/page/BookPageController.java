package ru.otus.ivan.page;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BookPageController {

    @GetMapping("/")
    public String listBooks() {
        return "books";
    }

    @GetMapping("/addBook")
    public String addBook() {
        return "addBook";
    }

    @GetMapping("/editBook/{bookId}")
    public String editBook(@PathVariable Long bookId, Model model) {
        model.addAttribute("id", bookId);
        return "editBook";
    }
}