package ru.otus.ivan.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.otus.ivan.dto.CommentDto;
import ru.otus.ivan.dto.CommentEditDto;
import ru.otus.ivan.service.CommentService;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/comments/{bookId}")
    public List<CommentDto> getAllComments(@PathVariable Long bookId) {
        return commentService.getAllByBookId(bookId);
    }

    @PostMapping("/comment/delete/{id}")
    public void deleteComment(@PathVariable Long id) {
        commentService.deleteById(id);
    }

    @PostMapping(value = "/comment/add/{bookId}", headers = "Accept=application/json")
    public void addComment(
            @PathVariable Long bookId,
            @RequestBody CommentEditDto comment) {
        comment.setBook(bookId);
        commentService.save(comment);
    }
}
