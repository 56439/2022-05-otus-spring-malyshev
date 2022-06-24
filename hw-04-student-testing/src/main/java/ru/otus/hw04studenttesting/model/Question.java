package ru.otus.hw04studenttesting.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.otus.hw04studenttesting.model.interfaces.IQuestion;

@Data
@AllArgsConstructor
public class Question implements IQuestion {

    private int id;
    private String question;
    private String rightAnswer;
}