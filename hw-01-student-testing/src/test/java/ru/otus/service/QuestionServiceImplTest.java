package ru.otus.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Класс QuestionServiceImpl")
public class QuestionServiceImplTest {

  private final static String RESOURCE = "questions-empty.csv";
  private QuestionServiceImpl questionService;

  @BeforeEach
  void setUp() {
    questionService = new QuestionServiceImpl(RESOURCE);
  }

  @Test
  @DisplayName(value = "Проверка пустого ресурса")
  void getQuestions() {
    String expected = "";
    String questions = questionService.getQuestions();

    assertEquals(expected, questions);
  }
}
