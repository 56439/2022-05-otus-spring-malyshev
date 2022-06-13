package ru.otus.hw03studenttesting.service;

import ru.otus.hw03studenttesting.model.User;

public interface QuizService {

    void quiz();

    User getUser();

    void setUser(User user);

    Integer getResult();

    void setResult(Integer result);
}