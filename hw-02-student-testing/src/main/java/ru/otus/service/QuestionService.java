package ru.otus.service;

import java.util.List;
import ru.otus.model.Question;

public interface QuestionService {

  List<Question> getQuestions();
}