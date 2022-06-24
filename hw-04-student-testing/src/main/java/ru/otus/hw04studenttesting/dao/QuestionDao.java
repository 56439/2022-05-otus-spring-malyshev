package ru.otus.hw04studenttesting.dao;

import ru.otus.hw04studenttesting.model.Question;

import java.util.List;

public interface QuestionDao {

    List<Question> getAll(String language);
}