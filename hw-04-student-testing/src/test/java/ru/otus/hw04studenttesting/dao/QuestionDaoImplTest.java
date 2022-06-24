package ru.otus.hw04studenttesting.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import ru.otus.hw04studenttesting.config.AppConfiguration;
import ru.otus.hw04studenttesting.model.Question;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = {QuestionDaoImpl.class, AppConfiguration.class})
@ActiveProfiles("test")
@DisplayName(value = "Класс QuestionDaoImpl")
public class QuestionDaoImplTest {

    @Autowired
    QuestionDaoImpl questionDao;

    @MockBean
    AppConfiguration configuration;

    @Test
    @DisplayName(value = "Вопросы на русском")
    void getAllRu() {
        Map<String, String> language = new HashMap<>();
        language.put("ru", "questions_ru.csv");

        Mockito.when(configuration.getQuestionsResources()).thenReturn(language);
        List<Question> questions = questionDao.getAll("ru");

        assertNotNull(questions);
    }

    @Test
    @DisplayName(value = "Вопросы на английском")
    void getAllEn() {
        Map<String, String> language = new HashMap<>();
        language.put("en", "questions_en.csv");

        Mockito.when(configuration.getQuestionsResources()).thenReturn(language);
        List<Question> questions = questionDao.getAll("en");

        assertNotNull(questions);
    }
}