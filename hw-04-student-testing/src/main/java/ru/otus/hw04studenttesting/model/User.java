package ru.otus.hw04studenttesting.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.otus.hw04studenttesting.model.interfaces.IUser;

@Data
@AllArgsConstructor
public class User implements IUser {

    private String name;
    private String surname;
}