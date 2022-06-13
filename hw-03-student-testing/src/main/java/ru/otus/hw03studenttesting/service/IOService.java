package ru.otus.hw03studenttesting.service;

import java.util.List;

public interface IOService {

    String input();

    void print(String message);

    void printList(List<String> list);

    void printBundledMessage(String bundledMessage);
}