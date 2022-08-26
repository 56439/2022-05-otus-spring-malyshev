package ru.otus.hw09library;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.otus.hw09library.dto.BookDto;
import ru.otus.hw09library.service.BookService;

import java.util.List;

@SpringBootApplication
@Slf4j
public class LibraryApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(LibraryApplication.class, args);
        /*BookService bookService = context.getBean(BookService.class);

        List<BookDto> bookDtos = bookService.getAll();
        log.info(bookDtos.toString());*/
    }
}