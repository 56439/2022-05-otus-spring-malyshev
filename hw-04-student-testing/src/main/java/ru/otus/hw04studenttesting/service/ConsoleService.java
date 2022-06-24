package ru.otus.hw04studenttesting.service;

import ru.otus.hw04studenttesting.model.User;

public interface ConsoleService {

    void process();

    String selectLanguage(String language);

    User login(String name, String surname);

    void showResult();
}