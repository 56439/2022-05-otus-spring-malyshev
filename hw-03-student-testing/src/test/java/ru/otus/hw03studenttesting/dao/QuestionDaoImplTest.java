package ru.otus.hw03studenttesting.dao;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.hw03studenttesting.config.AppConfiguration;
import ru.otus.hw03studenttesting.model.Question;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = {QuestionDaoImpl.class, AppConfiguration.class})
@DisplayName(value = "Класс QuestionDaoImpl")
public class QuestionDaoImplTest {

    @Autowired
    QuestionDaoImpl questionDao;

    @Test
    @DisplayName(value = "Вопросы на русском")
    void getAllRu() {
        List<Question> questions = questionDao.getAll("ru");

        assertNotNull(questions);
    }

    @Test
    @DisplayName(value = "Вопросы на английском")
    void getAllEn() {
        List<Question> questions = questionDao.getAll("en");

        assertNotNull(questions);
    }
}
