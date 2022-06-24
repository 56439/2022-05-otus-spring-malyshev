package ru.otus.hw03studenttesting.dao;

import ru.otus.hw03studenttesting.model.Question;

import java.util.List;

public interface QuestionDao {

    List<Question> getAll(String language);
}