package ru.otus.hw04studenttesting.service;

import ru.otus.hw04studenttesting.model.User;

public interface QuizService {

    void quiz();

    User getUser();

    void setUser(User user);

    Integer getResult();

    void setResult(Integer result);
}