package ru.otus.ivan.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.ivan.security.config.SecurityConfig;
import ru.otus.ivan.service.BookService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.otus.ivan.TestData.*;

@WebMvcTest(BookController.class)
@Import(SecurityConfig.class)
@DisplayName("Контроллер для работы с книгами")
public class BookControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BookService bookService;

    @Test
    @DisplayName("не получает список книг для не авторизированного пользователя")
    void getAllBooks_NotAuth_Test() throws Exception {
        Mockito.when(bookService.getAll()).thenReturn(BOOKS_DTO_LIST);
        mvc.perform(get("/api/books"))
                .andExpect(status().isFound());
    }

    @Test
    @WithMockUser(username = "userTest", roles = "USER")
    @DisplayName("получает список книг для авторизированного пользователя")
    void getAllBooks_Auth_Test() throws Exception {
        Mockito.when(bookService.getAll()).thenReturn(BOOKS_DTO_LIST);
        mvc.perform(get("/api/books"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("не получает книгу по ID для не авторизированного пользователя")
    void getBookById_NotAuth_Test() throws Exception {
        Mockito.when(bookService.getById(1L)).thenReturn(BOOK_DTO);
        mvc.perform(get("/api/book/1"))
                .andExpect(status().isFound());
    }

    @Test
    @WithMockUser(username = "userTest", roles = "USER")
    @DisplayName("получает книгу по ID для авторизированного пользователя")
    void getBookById_Auth_Test() throws Exception {
        Mockito.when(bookService.getById(1L)).thenReturn(BOOK_DTO);
        mvc.perform(get("/api/book/1"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("не удаляет книгу по ID для не авторизированного пользователя")
    void deleteBookById_NotAuth_Test() throws Exception {
        mvc.perform(post("/api/book/delete/1"))
                .andExpect(status().isFound());
    }

    @Test
    @WithMockUser(username = "userTest", roles = "USER")
    @DisplayName("удаляет книгу по ID для авторизированного пользователя")
    void deleteBookById_Auth_Test() throws Exception {
        mvc.perform(post("/api/book/delete/1"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("не добавляет книгу для не авторизированного пользователя")
    void addBook_NotAuth_Test() throws Exception {
        mvc.perform(
                post("/api/book/add")
                        .contentType("application/json")
                        .characterEncoding("utf-8")
                        .content(objectMapper.writeValueAsString(BOOK_EDIT_DTO))
        ).andExpect(status().isFound());
    }

    @Test
    @WithMockUser(username = "userTest", roles = "USER")
    @DisplayName("добавляет книгу для авторизированного пользователя")
    void addBook_Auth_Test() throws Exception {
        mvc.perform(
                post("/api/book/add")
                        .contentType("application/json")
                        .characterEncoding("utf-8")
                        .content(objectMapper.writeValueAsString(BOOK_EDIT_DTO))
                )
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("не обновляет книгу для не авторизированного пользователя")
    void updateBook_NotAuth_Test() throws Exception {
        mvc.perform(
                post("/api/book/update")
                        .contentType("application/json")
                        .characterEncoding("utf-8")
                        .content(objectMapper.writeValueAsString(BOOK_EDIT_DTO))
        ).andExpect(status().isFound());
    }

    @Test
    @WithMockUser(username = "userTest", roles = "USER")
    @DisplayName("обновляет книгу для авторизированного пользователя")
    void updateBook_Auth_Test() throws Exception {
        mvc.perform(
                post("/api/book/update")
                        .contentType("application/json")
                        .characterEncoding("utf-8")
                        .content(objectMapper.writeValueAsString(BOOK_EDIT_DTO))
        ).andExpect(status().isOk());
    }
}