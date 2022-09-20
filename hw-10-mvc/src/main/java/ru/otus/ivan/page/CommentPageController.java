package ru.otus.ivan.page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CommentPageController {

    @GetMapping("/comments/{bookId}")
    public String commentsList(@PathVariable Long bookId, Model model) {
        model.addAttribute("bookId", bookId);
        return "comments";
    }

    @GetMapping("/addComment/{bookId}")
    public String addComment(@PathVariable Long bookId, Model model) {
        model.addAttribute("bookId", bookId);
        return "addComment";
    }

}